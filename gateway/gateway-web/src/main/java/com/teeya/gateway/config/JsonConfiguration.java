package com.teeya.gateway.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.reactivestreams.Publisher;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.Encoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZJH
 * @Date: 2020/2/26 13:52
 */
//@Configuration  不起作用
public class JsonConfiguration {
    /**
     * 自定义json编码器
     */
    @Bean
    public CodecCustomizer jsonCodecCustomizer() {
        return configurer -> configurer.defaultCodecs().jackson2JsonEncoder(new JsonEncoder());
    }

    /**
     * json编码器
     */
    public static class JsonEncoder implements Encoder<Object> {

        @Override
        public boolean canEncode(ResolvableType elementType, MimeType mimeType) {
            return true;
        }

        @Override
        public Flux<DataBuffer> encode(Publisher<?> inputStream, DataBufferFactory bufferFactory, ResolvableType elementType,
                                       MimeType mimeType, Map<String, Object> hints) {
            if (inputStream instanceof Mono) {
                return Mono.from(inputStream)
                        .map(value -> encodeValue(value, bufferFactory))
                        .flux();
            }
            return null;
        }

        @Override
        public List<MimeType> getEncodableMimeTypes() {
            return Collections.singletonList(MimeTypeUtils.APPLICATION_JSON);
        }

        /**
         * 处理数据
         */
        private DataBuffer encodeValue(Object value, DataBufferFactory bufferFactory) {
            DataBuffer buffer = bufferFactory.allocateBuffer();
            byte[] bytes = JSON.toJSONBytes(value, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
            buffer.write(bytes);
            return buffer;
        }
    }
}
