package com.teeya.article.controller;


import com.teeya.article.entity.vo.ClassificationForm;
import com.teeya.article.service.ClassificationService;
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

    @Autowired
    private ClassificationService classificationService;

    @ApiOperation(value = "新增文章分类", notes = "新增一个文章分类")
    @ApiImplicitParam(name = "classificationForm", value = "新增文章分类form表单", required = true, dataType = "ClassificationForm")
    @PostMapping
    public int insert(@Valid @RequestBody ClassificationForm classificationForm) {
        return classificationService.insert(classificationForm);
    }

}

