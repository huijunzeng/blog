package com.teeya.gateway;


import com.teeya.gateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@RestController
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Autowired
    private AuthService authService;

    @GetMapping("/test")
    public String test() {
        return authService.hello();
        //return "success";
    }
    @GetMapping("/test2")
    public boolean test2() {
        System.out.println(111);
        System.out.println(111222);
        boolean b = authService.hasPermission("a", "a", "a");
        System.out.println("====================" + b);
        return b;
    }
}
