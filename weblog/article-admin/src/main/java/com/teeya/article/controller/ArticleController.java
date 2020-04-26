package com.teeya.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.*;
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
    @ApiImplicitParam(paramType = "form", name = "articleForm", value = "新增文章表单", required = true, dataType = "ArticleForm")
    @PostMapping
    public boolean save(@RequestBody ArticleSaveForm articleSaveForm) {
        return articleService.save(articleSaveForm);
    }

    @ApiOperation(value = "修改文章", notes = "修改文章")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "form", name = "articleUpdateForm", value = "文章修改表单", required = true, dataType = "ArticleUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable String id, @RequestBody ArticleUpdateForm articleUpdateForm) {
        return articleService.update(id, articleUpdateForm);
    }

    @ApiOperation(value = "获取文章", notes = "根据文章id获取指定文章信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataType = "String")
    @GetMapping(value = "/{id}")
    public ArticleEntity get(@PathVariable String id) {
        log.info("articleId: " + id);
        return articleService.get(id);
    }

    @ApiOperation(value = "搜索文章", notes = "根据条件获取文章信息列表")
    @ApiImplicitParam(paramType = "form", name = "articleQueryForm", value = "文章查询表单", required = true, dataType = "ArticleQueryForm")
    @PostMapping(value = "/list")
    public IPage queryList(@RequestBody ArticleQueryForm articleQueryForm) {
        log.info("articleQueryForm:{}", articleQueryForm);
        return articleService.queryList(articleQueryForm);
    }

    @ApiOperation(value = "删除文章", notes = "根据id删除文章")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataType = "String")
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable String id) {
        return articleService.remove(id);
    }
}

