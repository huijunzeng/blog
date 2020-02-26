package com.teeya.gateway.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * feign调取服务时，携带上token
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
// feign调取接口时报以上的错误   配置这个时起作用，可以解决  todo
@Configuration
public class FeignConfig /*implements RequestInterceptor*/ {

    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
    }

    @Bean
    public MappingJackson2HttpMessageConverter httpMessageConverter() {
        MediaType[] mediaTypes = new MediaType[]{
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_OCTET_STREAM,
                MediaType.APPLICATION_JSON_UTF8,
                MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_XML,
                MediaType.APPLICATION_ATOM_XML,
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_PDF,
        };
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(mediaTypes));
        return converter;
    }

    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
        //final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
        return new ObjectFactory<HttpMessageConverters>() {
            @Override
            public HttpMessageConverters getObject() throws BeansException {
                return httpMessageConverters;
            }
        };
    }

    /*public class MappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        PhpMappingJackson2HttpMessageConverter(){
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")); //关键
            mediaTypes.add(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE)); //关键
            setSupportedMediaTypes(mediaTypes);
        }
    }*/

    /*@Override
    public void apply(RequestTemplate requestTemplate) {
        *//*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = null;
        String platform = null;
        if(attributes != null) {
            HttpServletRequest request1 = attributes.getRequest();
            token = request.getHeader("token");
            platform = request.getHeader("p_platform");
        }

        //添加token
        requestTemplate.header("token", token);
        requestTemplate.header("p_platform", platform);*//*
    }*/
}
