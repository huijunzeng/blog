package com.teeya.gateway.config;

import com.teeya.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * @Author: ZJH
 * @Date: 2020/2/15 17:21
 */

@Component
@RestControllerAdvice // 与@RestController类型，相当于@ResponseBody与@ControllerAdvice的结合体
public class GlobalExceptionHandler {

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
     * @param e
     * @return json格式类型
     */
    @ExceptionHandler(RuntimeException.class)//指定拦截异常的类型 可数组
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //自定义浏览器返回状态码
    public Map<String, Object> customExceptionHandler(RuntimeException e) {
        System.out.println("异常： " + 888);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        //map.put("msg", e.getErrorType().getMsg());
        return map;
    }
}
