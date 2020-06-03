package com.teeya.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@SpringBootApplication(scanBasePackages = {"com.teeya.common.web.swagger"}) 当要引入common模块的配置类时，需要用到scanBasePackages属性，等价于@ComponentScan(basePackages = "com.teeya.common.web.swagger")
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.teeya.user.mapper"}) //扫描mapper层  逆向工程生成代码时没有在mapper接口加注解@Mapper，所以另一解决办法就是在mapper层加上@Mapper这个注解，但前者更方便省代码
public class AdminUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminUserApplication.class, args);
    }

}
