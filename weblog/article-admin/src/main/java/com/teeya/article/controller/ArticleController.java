package com.teeya.article.controller;


import com.teeya.article.entity.form.ArticleForm;
import com.teeya.article.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 博客文章表 前端控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/article")
@Api(value = "article", tags = {"文章操作restful接口"})
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "新增文章", notes = "新增一篇文章")
    @ApiImplicitParam(name = "articleForm", value = "新增文章form表单", required = true, dataType = "ArticleForm")
    @PostMapping
    public void insert(@Valid @RequestBody ArticleForm articleForm) {
        articleService.insert(articleForm);
    }
}

