package com.teeya.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teeya.gateway.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 路由请求鉴权过滤器
 * 在路由到某个服务之前做一些额外处理
 */

@Configuration
//@ComponentScan(basePackages = "com.teeya.client")// 扫描引入authentication-client模块的jar包下的bean  扫描路径需要与application启动类的上一级路径保持一致  比如模块A的application启动类的上一级路径为com.xx,模块B的路径也需要为com.xx才能被扫描到
@Slf4j
public class AccessFilter implements GlobalFilter {

    /**
     * Authorization认证开头是"bearer "
     */
    private static final String BEARER = "Bearer ";

    /**
     * 请求头变量key
     */
    private static final String X_CLIENT_USER = "X-Client-User";

    /**
     * 鉴权客户端服务
     */
    @Autowired
    private AuthService authService;

    /**
     * 获取请求头token，检验token是否有效以及合法
     * 有效合法则调取authentication-client鉴权客户端判断用户是否拥有该权限
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求头Authorization的内容
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        log.info("token：{}", token);
        // 获取请求方法
        String method = request.getMethodValue();
        // 获取请求url
        String url = request.getPath().value();
        log.info("url:{},method:{},headers:{}", url, method, request.getHeaders());
        // 忽视签权的url（如用户登录操作）
        if (authService.isIgnoreAuthenticationUrl(url)) {
            log.info("忽视鉴权的url:{}", url);
            return chain.filter(exchange);
        }

        // token不能为空
        if (StringUtils.isBlank(token)) {
            log.error("user token is null");
            return unauthorized(exchange);
        }

        // 需要签权的url，判断用户是否有该资源的权限  服务调用需要携带上token，要做特殊处理
        if (authService.hasPermission(token, url, method)) {
            log.info("进入鉴权判断");
            ServerHttpRequest.Builder builder = request.mutate();
            //将jwt token中的用户信息传给服务
            // 信息安全  做加密  todo
            // 可以根据个人需要在转发的请求头加上token解析后的body的信息； 原有的请求头信息不受影响，会路由到具体服务中
            builder.header(X_CLIENT_USER, this.checkTokenAndParseAsJson(token));
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        return unauthorized(exchange);
    }


    /**
     * 将checkToken端点返回的token信息转为json，然后保存到请求头
     * {
     * 	"aud": ["blog"],
     * 	"user_name": "admin",
     * 	"scope": ["read"],
     * 	"organization": "admin",
     * 	"exp": 1584021426,
     * 	"authorities": ["R001"],
     * 	"jti": "db6861b9-a5e5-45d6-ac9c-59663e5b5e7d",
     * 	"client_id": "test_client"
     * }
     *
     * @param token
     * @return
     */
    private String checkTokenAndParseAsJson(String token) {
        String tokenBodyInfo = "{}";
        try {
            String substringToken = StringUtils.substring(token, BEARER.length());
            log.info("substring========:{} ", substringToken);
            tokenBodyInfo = new ObjectMapper().writeValueAsString(authService.checkToken(substringToken));
            log.info("checkTokenAndParseAsJson========:{}", tokenBodyInfo);
            return tokenBodyInfo;
        } catch (JsonProcessingException e) {
            log.error("token json error:{}", e.getMessage());
        }
        return tokenBodyInfo;
    }

    /**
     * 网关拒绝，返回401
     * @param serverWebExchange
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
