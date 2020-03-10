package com.teeya.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: ZJH
 * @Date: 2020/3/5 17:40
 */

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan({"com.teeya.article.mapper"}) //扫描mapper层  逆向工程生成代码时没有在mapper接口加注解@Mapper，所以另一解决办法就是在mapper层加上@Mapper这个注解，但前者更方便省代码
public class ArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }
}
