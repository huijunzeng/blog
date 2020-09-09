package com.teeya.gateway.config;

import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;

/**
 * feign调取服务时返回json格式内容  配置feign的json解析器
 * @Author: ZJH
 * @Date: 2020/2/24 13:29
 */

/**
 * feign.codec.DecodeException: No qualifying bean of type 'org.springframework.boot.autoconfigure.http.HttpMessageConverters' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
 * 	at feign.SynchronousMethodHandler.decode(SynchronousMethodHandler.java:182)
 * 	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
 * Error has been observed at the following site(s):
 * 	|_ checkpoint ⇢ org.springframework.cloud.gateway.filter.WeightCalculatorWebFilter [DefaultWebFilterChain]
 * 	|_ checkpoint ⇢ com.alibaba.csp.sentinel.adapter.spring.webflux.SentinelWebFluxFilter [DefaultWebFilterChain]
 * 	|_ checkpoint ⇢ org.springframework.boot.actuate.metrics.web.reactive.server.MetricsWebFilter [DefaultWebFilterChain]
 * 	|_ checkpoint ⇢ HTTP GET "/test2" [ExceptionHandlingWebHandler]
 * Stack trace:
 * 		at feign.SynchronousMethodHandler.decode(SynchronousMethodHandler.java:182)
 */
// feign调取接口时报以上的错误   配置feign的json解析器，可以解决
@Configuration
@Slf4j
public class FeignJsonConfiguration {

    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
    }

    @Bean
    public MappingJackson2HttpMessageConverter httpMessageConverter() {
        MediaType[] mediaTypes = new MediaType[]{
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_OCTET_STREAM,
                MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_XML,
                MediaType.APPLICATION_ATOM_XML,
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_PDF,
        };
        // 默认Spring的Jackson解析库
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(mediaTypes));
        return converter;
    }

    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
        return () -> httpMessageConverters;
        // 以上为简写lambda表达式，相当于以下的匿名内部类
        /*return new ObjectFactory<HttpMessageConverters>() {
            @Override
            public HttpMessageConverters getObject() throws BeansException {
                return httpMessageConverters;
            }
        };*/
    }
}
