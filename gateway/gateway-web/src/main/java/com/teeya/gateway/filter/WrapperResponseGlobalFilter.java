package com.teeya.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.teeya.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 统一响应结果格式处理
 *
 * todo1、修改请求body的问题，2、修改返回Body的问题，3、返回数据过长被截断问题 4、动态路由问题
 * @Author: ZJH
 * @Date: 2020/3/2 17:03
 */
@Component
@Slf4j
public class WrapperResponseGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        // -1 is response write filter, must be called before that
        return -2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        /*ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String s = new String(content, Charset.forName("UTF-8"));
                        System.out.println("response: " +s);
                        //TODO，s就是response的值，想修改、查看就随意而为了
                        byte[] uppedContent = new String(content, Charset.forName("UTF-8")).getBytes();
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
        // replace response with decorator
        return chain.filter(exchange.mutate().response(decoratedResponse).build());*/


        HttpStatus statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                        //最重要的是这里，网上有很多对gateway响应进行修改的
                        //但是我这里会进行截断（并不是每次都截）
                        //刚开始也是按照buffer一个一个读，
                        // 然后看了下api 发现可以获取出所有流的集合
                        // 然后又继续查api 发现  DataBufferFactory 可以进行合并多个流的集合
                        //然后就把这个问题解决了。
                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                        DataBuffer join = dataBufferFactory.join(dataBuffers);
                        byte[] content = new byte[join.readableByteCount()];
                        join.read(content);
                        //释放掉内存
                        DataBufferUtils.release(join);

                                   /*之前是这么写的但是效率偏低，因为会copy几次
                                  List<byte[]> list = new ArrayList();
                               dataBuffers.forEach(dataBuffer -> {
                                    byte[] content = new byte[dataBuffer.readableByteCount()];
                                    dataBuffer.read(content);
                                    DataBufferUtils.release(dataBuffer);
                                    list.add(content);
                                });
                            byte[] bytes = byteMergerAll(list);*/

                        String responseData = new String(content, StandardCharsets.UTF_8);

                        //responseData就是下游系统返回的内容,可以查看修改  todo
                        log.info("响应内容:{}", responseData);
                        System.out.println("responseData: " + responseData);
                        // todo
                        ResponseResult responseResult = new ResponseResult(HttpStatus.OK.value(), "success", responseData);
                        //byte[] uppedContent = new String(responseData.getBytes(), StandardCharsets.UTF_8).getBytes();
                        byte[] uppedContent = new String(JSONObject.toJSONString(responseResult).getBytes(), StandardCharsets.UTF_8).getBytes();
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
            };
            return chain.filter(exchange.mutate().response(decoratedResponse).build());
        }
            return chain.filter(exchange);
    }
}
