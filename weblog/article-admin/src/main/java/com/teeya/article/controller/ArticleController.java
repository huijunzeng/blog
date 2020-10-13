package com.teeya.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.*;
import com.teeya.article.entity.pojo.ArticleEntity;
import com.teeya.article.service.ArticleService;
import com.teeya.common.core.entity.vo.R;
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
    @ApiImplicitParam(paramType = "body", name = "articleForm", value = "新增文章表单", required = true, dataTypeClass = ArticleSaveForm.class)
    @PostMapping
    public R<Boolean> save(@Validated @RequestBody ArticleSaveForm articleSaveForm) {
        return R.success(articleService.save(articleSaveForm));
    }

    @ApiOperation(value = "修改文章", notes = "修改文章")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(paramType = "body", name = "articleUpdateForm", value = "文章修改表单", required = true, dataTypeClass = ArticleUpdateForm.class)})
    @PutMapping(value = "/{id}")
    public R<Boolean> update(@PathVariable Long id, @Validated @RequestBody ArticleUpdateForm articleUpdateForm) {
        return R.success(articleService.update(id, articleUpdateForm));
    }

    @ApiOperation(value = "获取文章", notes = "根据文章id获取指定文章信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public R<ArticleEntity> get(@PathVariable Long id) {
        log.info("articleId: " + id);
        return R.success(articleService.get(id));
    }

    @ApiOperation(value = "搜索文章", notes = "根据条件获取文章信息列表")
    @ApiImplicitParam(paramType = "body", name = "articleQueryForm", value = "文章查询表单", required = true, dataTypeClass = ArticleQueryForm.class)
    @PostMapping(value = "/list")
    public R<IPage> queryList(@RequestBody ArticleQueryForm articleQueryForm) {
        log.info("articleQueryForm:{}", articleQueryForm);
        return R.success(articleService.queryList(articleQueryForm));
    }

    @ApiOperation(value = "删除文章", notes = "根据id删除文章")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章id", required = true, dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    public R<Boolean> remove(@PathVariable Long id) {
        return R.success(articleService.remove(id));
    }
}

