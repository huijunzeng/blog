package com.teeya.user.exception;

import com.teeya.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常处理
 * @Author: ZJH
 * @Date: 2019/10/15 17:21
 */
// 与@RestController类型，相当于@ResponseBody与@ControllerAdvice的结合体
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends DefaultGlobalExceptionHandlerAdvice {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initWebBinder(WebDataBinder binder){

    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("attribute",  "The Attribute");
    }

    /**
     * 捕获CustomizeException自定义异常
     * @param ex
     * @return json格式类型
     */
    /*@ExceptionHandler(RuntimeException.class)//指定拦截异常的类型 可数组
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //自定义浏览器返回状态码
    public Map<String, Object> customExceptionHandler(CustomizeException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", e.getResponse().getCode());
        map.put("msg", e.getResponse().getMsg());
        return map;
    }*/

    /*@ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handle(Exception ex) {
        log.error("exception:{}", ex);
        Map<String, Object> map = new HashMap<>();
        if (ex instanceof BaseException){
            BaseException baseException = (BaseException) ex;
            //map.put("msg", ex1.getMsg());
            map.put("code", 404);
            map.put("msg", baseException.getMessage());
        } else if (ex instanceof ArithmeticException) {
            map.put("code", 404);
            map.put("msg", "算法异常");
        } else {// 统一处理服务间内部调用异常
            map.put("code", 500);
            map.put("msg", "内部错误");
        }
        log.error("exception_map:{}", map.toString());
        return map;
    }*/

    /*@ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handle(Throwable throwable) {
        Map<String, Object> map = new HashMap<>();
        log.error("throwable_map:{}", throwable.toString());
        *//*if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else*//*
        if (throwable instanceof Exception) {
            map = handle((Exception) throwable);
        }
        return map;
    }*/
}
