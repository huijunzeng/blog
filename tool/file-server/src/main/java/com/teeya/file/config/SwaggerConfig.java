package com.teeya.file.config;

import com.fasterxml.classmate.GenericType;
import com.fasterxml.classmate.TypeResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * swagger接口文档配置
 * @Author: ZJH
 * @Date: 2019/12/5 10:15
 */

@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // swagger接口界面访问路径 ：http://localhost:9800/swagger-ui.html  IP为机器的IP，端口号为工程的端口

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // api接口路径，即controller层路径
                .apis(RequestHandlerSelectors.basePackage("com.teeya.file"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .alternateTypeRules(
                        newRule(
                                typeResolver.resolve(
                                        DeferredResult.class,
                                        typeResolver.resolve(GenericType.class, WildcardType.class)
                                ),
                                typeResolver.resolve(WildcardType.class)
                        )
                )
                // 支持的协议
                .protocols(newHashSet("https", "http"))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文件服务器管理api")
                .description("文件服务器管理接口")
                .version("2.0")
                .build();
    }

    /**
     * 设置授权信息
     */
    private List<ApiKey> securitySchemes() {
        // 在请求头header添加一个名为Authorization的token
        return Collections.singletonList(new ApiKey(HttpHeaders.AUTHORIZATION, "token", "header"));
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(
                                Collections.singletonList(new SecurityReference("Authorization",
                                        new AuthorizationScope[]{new AuthorizationScope("global", "")}
                                )))
                        // 可通过配置正则表达式去排除一些不需要携带token访问的接口 这里不做特殊处理，全部接口访问都需要携带
                        // 比如.forPaths(PathSelectors.regex("^(?!auth).*$"))  对所有包含"auth"的接口不需要使用securitySchemes
                        .forPaths(PathSelectors.any())
                        .build()
        );
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }
}
