package com.teeya.authentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/hello")
    public String test(){
        logger.info("ceshi11111");
        logger.debug("ceshi22222");
        logger.error("ceshi33333");
        return "hello oauth";
    }

    @GetMapping("/principal")
    //@PreAuthorize("hasAnyAuthority('USER')")
    public Principal user(Principal principal) {
        return principal;
    }
}
