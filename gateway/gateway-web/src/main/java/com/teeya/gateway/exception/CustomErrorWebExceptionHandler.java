package com.teeya.gateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 默认是由DefaultErrorWebExceptionHandler处理异常的
 * 继承该类并重写对应的方法，然后在ExceptionHandlerConfiguration类配置生效
 * @Author: ZJH
 * @Date: 2020/3/2 9:26
 */
@Slf4j
public class CustomErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

    @Autowired
    private GateWayExceptionHandlerAdvice gateWayExceptionHandlerAdvice;

    public CustomErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 返回路由方法基于renderErrorResponse方法的ServerResponse的对象，因此可以重写renderErrorResponse方法自定义异常的返回格式
     * @param errorAttributes
     * @return
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * 渲染异常ServerResponse对象
     * renderErrorResponse方法的ServerResponse对象封装依赖三个方法isIncludeStackTrace、getErrorAttributes、getHttpStatus，根据源码ServerResponse对象的body属性才是最终返回给用户，所以我们只需要改造封装body属性。默认是直接封装getErrorAttributes方法返回的error集合，因此我们可以自定义封装返回参数
     默认返回格式参数为：{
     *     "timestamp": "2020-02-28T07:15:51.040+0000",
     *     "path": "/admin-user/user",
     *     "status": 500,
     *     "error": "Internal Server Error",
     *     "message": "status 401 reading AuthenticationProvider#hasPermission(String,String,String)",
     *     "requestId": "dabeac33"
     * }
     * @param request
     * @return
     */
    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        boolean includeStackTrace = this.isIncludeStackTrace(request, MediaType.ALL);
        Map<String, Object> error = this.getErrorAttributes(request, includeStackTrace);
        Throwable throwable = this.getError(request);
        int httpStatus = this.getHttpStatus(error);
        System.out.println("code：" + httpStatus);
        return ServerResponse.status(this.getHttpStatus(error))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(gateWayExceptionHandlerAdvice.handle(throwable)));
    }

}
