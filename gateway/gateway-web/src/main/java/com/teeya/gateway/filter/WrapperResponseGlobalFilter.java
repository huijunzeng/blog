package com.teeya.gateway.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.teeya.common.core.entity.vo.R;
import com.teeya.common.core.exception.BusinessException;
import com.teeya.common.core.util.JSONUtils;
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

    /**确保过滤器最先执行*/
    @Override
    public int getOrder() {
        // -1 is response write filter, must be called before that
        return -2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        log.info("response====:{}", JSONUtils.objectToJson(response));
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
                        log.info("响应内容responseData:{}", responseData);
                        // responseData就是下游服务返回的内容,可以查看修改
                        Object object = JSONUtils.jsonToPojo(responseData, Object.class);
                        log.info("object instanceof BaseException:{}", object instanceof BusinessException);
                        // 判断下游服务返回的是正常的响应数据还是异常信息
                        JsonNode jsonNode = JSONUtils.jsonToTree(responseData, null);
                        if (jsonNode.isArray()) {// 数组类型
                            jsonNode = jsonNode.get(0);
                        } else {// 其他类型
                            log.info("object========:{}", object);
                        }
                        log.info("parseObject========:{}", JSONUtils.objectToJson(jsonNode));
                        if (object != null && jsonNode.has("code") && jsonNode.has("msg")) {
                            // 自定义异常信息不封装直接返回
                            log.info("error:{}", responseData);
                        } else if (jsonNode != null && jsonNode.has("error")) {
                            // /oauth/token端点异常：{"error":"unauthorized","error_description":null}
                        } else if (jsonNode != null && jsonNode.has("components")) {
                            // swagger接口文档不封装直接返回
                        } else {
                            log.info("normal invoke return");
                            R r = new R(HttpStatus.OK.value(), "success", object);
                            responseData = JSONUtils.objectToJson(r);
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
