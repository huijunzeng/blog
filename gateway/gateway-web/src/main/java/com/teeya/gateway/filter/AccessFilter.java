package com.teeya.gateway.filter;

//import com.teeya.client.service.AuthService;
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

/**
 * 请求鉴权过滤器
 */

@Configuration
@Slf4j
public class AccessFilter implements GlobalFilter {

    /**
     * 鉴权客户端服务
     */
    //@Autowired
    //private AuthService authService;

    /**
     * 获取请求头token，检验token是否有效以及合法
     * 有效合法则调取authentication-client鉴权客户端判断用户是否拥有该权限
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求头Authorization的内容
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        // 获取请求方法
        String method = request.getMethodValue();
        // 获取请求url
        String url = request.getPath().value();
        log.info("url:{},method:{},headers:{}", url, method, request.getHeaders());

        // 忽视签权的url（如用户登录操作）
        /*if (authService.isIgnoreAuthenticationUrl(url)) {
            return chain.filter(exchange);
        }*/

        // token不能为空
        if (StringUtils.isBlank(token)) {
            log.error("user token is null");
            System.out.println("user token is null");
            return unauthorized(exchange);
        }

        // 需要签权的url，判断用户是否有该资源的权限
        if (StringUtils.isBlank(token)) {
            log.error("user token is null");
            System.out.println("user token is null");
            return unauthorized(exchange);
        }

        /*if (authService.hasPermission(url)) {
            ServerHttpRequest.Builder builder = request.mutate();
            //TODO 转发的请求都加上服务间认证token
            //builder.header(X_CLIENT_TOKEN, "TODO zhoutaoo添加服务间简单认证");
            //将jwt token中的用户信息传给服务
            //builder.header(X_CLIENT_TOKEN_USER, getUserToken(authentication));
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }*/

        return chain.filter(exchange);
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
