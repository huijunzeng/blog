package com.teeya.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }


    @RestController
    @RequestMapping("/oauth2_token")
    public class UserController {

        @GetMapping("/principal")
        //@PreAuthorize("hasAnyAuthority('all')")
        public Principal user(Principal principal) {
            return principal;
        }

        @GetMapping("/query")
        @PreAuthorize("hasAnyAuthority('all')")
        public String all () {
            return "具有 all 权限";
        }
    }
}
