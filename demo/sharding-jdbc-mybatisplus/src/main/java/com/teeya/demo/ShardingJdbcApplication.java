package com.teeya.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.teeya.demo.mapper"}) //扫描mapper层  逆向工程生成代码时没有在mapper接口加注解@Mapper，所以另一解决办法就是在mapper层加上@Mapper这个注解，但前者更方便省代码
@EnableFeignClients
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }
}
