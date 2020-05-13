package com.teeya.article.exception;

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

}
