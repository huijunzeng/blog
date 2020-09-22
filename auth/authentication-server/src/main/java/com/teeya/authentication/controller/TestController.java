package com.teeya.authentication.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.teeya.common.core.entity.vo.R;
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

    // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 static 函数.
    // @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    // 若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑
    @SentinelResource(value = "test", blockHandler = "exceptionHandler", fallback = "helloFallback")
    @RequestMapping("/hello")
    public R<String> test(){
        logger.info("ceshi11111");
        logger.debug("ceshi22222");
        logger.error("ceshi33333");
        return R.success("hello oauth");
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String helloFallback(long s) {
        return String.format("Halooooo %d", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public R<String> exceptionHandler(long s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return R.success("Oops, error occurred at " + s);
    }

    @GetMapping("/principal")
    //@PreAuthorize("hasAnyAuthority('USER')")
    public R<Principal> user(Principal principal) {
        return R.success(principal);
    }
}
