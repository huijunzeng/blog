package com.teeya.article.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章分类表 前端控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/classification")
@Api(value = "classification", tags = {"文章分类操作restful接口"})
@Slf4j
public class ClassificationController {

}

