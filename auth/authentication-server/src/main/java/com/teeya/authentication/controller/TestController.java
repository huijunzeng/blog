package com.teeya.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String test(){
        return "hello oauth";
    }

    @GetMapping("/principal")
    //@PreAuthorize("hasAnyAuthority('USER')")
    public Principal user(Principal principal) {
        return principal;
    }
}
