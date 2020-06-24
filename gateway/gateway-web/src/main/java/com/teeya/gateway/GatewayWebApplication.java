package com.teeya.gateway;


import com.teeya.gateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@EnableDiscoveryClient
@EnableFeignClients //调取其他服务的接口时必须加上这个注解
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}) //排除数据库配置
@SpringBootApplication
@RestController
public class GatewayWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class, args);
    }

    @Autowired
    private AuthService authService;
    @Resource
    private RouteLocator routeLocator;

    @GetMapping("/test")
    public String test() {
        return authService.hello();
        //return "success";
    }
    @GetMapping("/test2")
    public boolean test2() {
        Flux<Route> routes = routeLocator.getRoutes();
        Map<String, String> routesMap = new HashMap<>(8);
        routes.subscribe(route -> routesMap.put(route.getUri().getHost(), route.getUri().getHost()));
        System.out.println("==========:" + routesMap.toString());
       /* System.out.println(111222);
        boolean b = authService.hasPermission("a", "a", "a");
        System.out.println("====================" + b);*/
        return true;
    }
}
