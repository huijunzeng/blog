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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 博客文章表 前端控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 * 直接在方法内校验非实体类的单个参数时，需要加上@Validated注解（加@Valid无效）
 */
@RestController
@RequestMapping("/article")
@Api(value = "article", tags = {"文章操作restful接口"})
@Slf4j
@Validated
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "新增文章", notes = "新增一篇文章")
    @ApiImplicitParam(paramType = "form", name = "articleForm", value = "新增文章表单", required = true, dataType = "ArticleForm")
    @PostMapping
    public boolean save(@Validated @RequestBody ArticleSaveForm articleSaveForm) {
        return articleService.save(articleSaveForm);
    }

    @ApiOperation(value = "修改文章", notes = "修改文章")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "form", name = "articleUpdateForm", value = "文章修改表单", required = true, dataType = "ArticleUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable Long id, @Validated @RequestBody ArticleUpdateForm articleUpdateForm) {
        return articleService.update(id, articleUpdateForm);
    }

    @ApiOperation(value = "获取文章", notes = "根据文章id获取指定文章信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataType = "Long")
    @GetMapping(value = "/{id}")
    public ArticleEntity get(@PathVariable Long id) {
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
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Long id) {
        return articleService.remove(id);
    }
}

