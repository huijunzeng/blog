package com.teeya.gateway.routes;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 实现动态路由
 * 可通过两种方式实现：InMemoryRouteDefinitionRepository内存方式 以及自定义redis方式
 * @Author: ZJH
 * @Date: 2020/3/3 11:35
 */
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return null;
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
