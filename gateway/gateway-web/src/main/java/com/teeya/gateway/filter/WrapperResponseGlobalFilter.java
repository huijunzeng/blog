package com.teeya.gateway.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.teeya.common.core.entity.vo.ResponseResult;
import com.teeya.common.core.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 统一正常响应结果格式处理  修改返回Body
 *
 * @Author: ZJH
 * @Date: 2020/3/2 17:03
 */
@Configuration
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
        log.info("response====:{}", JSONObject.toJSONString(response));
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                        // DataBufferFactory 可以进行合并多个流的集合
                        // 处理返回数据过长被截断的问题
                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                        DataBuffer join = dataBufferFactory.join(dataBuffers);
                        byte[] content = new byte[join.readableByteCount()];
                        join.read(content);
                        // 释放掉内存
                        DataBufferUtils.release(join);
                        String responseData = new String(content, StandardCharsets.UTF_8);

                        // responseData就是下游服务返回的内容,可以查看修改
                        Object object = JSONObject.parseObject(responseData, Object.class);
                        log.info("object instanceof BaseException:{}", object instanceof BaseException);
                        log.info("响应内容responseData:{}", responseData);
                        // 判断下游服务返回的是正常的响应数据还是异常信息
                        JSONObject parseObject = null;
                        if (object instanceof JSONObject) {// 对象类型
                            parseObject = JSONObject.parseObject(responseData);
                        } else if (object instanceof JSONArray) {// 数组类型
                            String value = JSONObject.toJSONString(JSONObject.parseArray(responseData, Object.class).get(0));
                            parseObject = JSONObject.parseObject(value);
                        } else {// 其他类型
                            log.info("object========:{}", object);
                        }
                        log.info("parseObject========:{}", JSONObject.toJSONString(parseObject));
                        if (parseObject != null && parseObject.containsKey("code") && parseObject.containsKey("msg")) {
                            // 自定义异常信息不封装直接返回
                            log.info("error:{}", responseData);
                        } else if (parseObject != null && parseObject.containsKey("error")) {
                            // /oauth/token端点异常：{"error":"unauthorized","error_description":null}
                        } else if (parseObject != null && parseObject.containsKey("swagger")) {
                            // swagger接口文档不封装直接返回
                        } else {
                            log.info("normal invoke return");
                            ResponseResult responseResult = new ResponseResult(HttpStatus.OK.value(), "success", object);
                            responseData = JSONObject.toJSONString(responseResult);
                        }
                        byte[] uppedContent = new String(responseData.getBytes(), StandardCharsets.UTF_8).getBytes();
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
            };
            return chain.filter(exchange.mutate().response(decoratedResponse).build());
        }
        return chain.filter(exchange);
    }
}
