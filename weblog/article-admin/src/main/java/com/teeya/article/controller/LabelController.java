package com.teeya.article.controller;

import com.teeya.article.entity.vo.LabelForm;
import com.teeya.article.service.LabelService;
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
 * 文章标签表 前端控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/label")
@Api(value = "label", tags = {"文章标签操作restful接口"})
@Slf4j
public class LabelController {

    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "新增文章标签", notes = "新增一个文章标签")
    @ApiImplicitParam(name = "labelForm", value = "新增文章标签form表单", required = true, dataType = "LabelForm")
    @PostMapping
    public int insert(@Valid @RequestBody LabelForm labelForm) {
        return labelService.insert(labelForm);
    }

}

