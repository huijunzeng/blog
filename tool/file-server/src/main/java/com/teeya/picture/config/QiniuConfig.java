package com.teeya.picture.config;

import com.qiniu.storage.Region;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZJH
 * @Date: 2020/4/9 13:38
 */
@Configuration
public class QiniuConfig {

    // 读取配置文件中七牛云的公私钥以及存储空间名
    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    /** 获取七牛云的Configuration */
    @Bean
    public com.qiniu.storage.Configuration getQiniuConfig() {
        // 华东机房region0 华北机房region1 华南机房region2
        return  new com.qiniu.storage.Configuration(Region.region2());
    }

    /** 获取Auth */
    @Bean
    public Auth getAuth() {
        return Auth.create(accessKey, secretKey);
    }
}
