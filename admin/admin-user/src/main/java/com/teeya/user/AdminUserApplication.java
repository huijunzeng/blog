package com.teeya.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.teeya.user.mapper"}) //注意，导入的必须要是tk包下面的注解才能使用通用mapper的方法 需要扫描mapper层  逆向工程生成代码时没有在mapper接口加注解@Mapper，所以另一解决办法就是在mapper层加上@Mapper这个注解，但前者更方便省代码
public class AdminUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminUserApplication.class, args);
    }

}
