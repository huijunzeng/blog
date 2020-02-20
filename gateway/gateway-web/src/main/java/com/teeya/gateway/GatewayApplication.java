package com.teeya.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.teeya.client")
@ComponentScan(basePackages = "com.teeya.client")// 扫描引入的authentication-client的jar包下的bean  扫描路径需要与application启动类的上一级路径保持一致  比如模块A的application启动类的上一级路径为com.xx,模块B的路径也需要为com.xx才能被扫描到,
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@RestController
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @GetMapping("/test")
    public String test() {
        return "success";
    }
}
