package com.teeya.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.ArticleForm;
import com.teeya.article.entity.form.ArticleQueryForm;
import com.teeya.article.entity.form.ArticleUpdateForm;
import com.teeya.article.entity.pojo.ArticleEntity;
import com.teeya.article.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 博客文章表 前台页面展示控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/web")
@Api(value = "article", tags = {"前台页面展示文章操作restful接口"})
@Slf4j
public class FrontPageController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "获取文章", notes = "根据文章id获取指定文章信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataType = "string")
    @GetMapping(value = "/article/{id}")
    public ArticleEntity get(@PathVariable String id) {
        log.info("articleId: " + id);
        return articleService.get(id);
    }

    @ApiOperation(value = "搜索文章", notes = "根据条件获取文章信息列表")
    @ApiImplicitParam(name = "articleQueryForm", value = "文章查询参数", required = true, dataType = "ArticleQueryForm")
    @PostMapping(value = "/article/list")
    public IPage queryList(@RequestBody ArticleQueryForm articleQueryForm) {
        log.info("articleQueryForm:{}", articleQueryForm);
        return articleService.queryList(articleQueryForm);
    }

}

