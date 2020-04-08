package com.teeya.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.ClassificationForm;
import com.teeya.article.entity.form.ClassificationQueryForm;
import com.teeya.article.entity.form.ClassificationUpdateForm;
import com.teeya.article.entity.pojo.ClassificationEntity;
import com.teeya.article.service.ClassificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiImplicitParam(name = "classificationForm", value = "新增文章分类表单", required = true, dataType = "ClassificationForm")
    @PostMapping
    public boolean save(@RequestBody ClassificationForm classificationForm) {
        return classificationService.save(classificationForm);
    }

    @ApiOperation(value = "修改文章分类", notes = "修改文章分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "文章分类id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "labelUpdateForm", value = "文章分类修改表单", required = true, dataType = "LabelUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable String id, @RequestBody ClassificationUpdateForm classificationUpdateForm) {
        return classificationService.update(id, classificationUpdateForm);
    }

    @ApiOperation(value = "获取文章分类", notes = "根据文章分类id获取指定文章分类信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章分类id", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public ClassificationEntity get(@PathVariable String id) {
        log.info("classificationId: " + id);
        return classificationService.get(id);
    }

    @ApiOperation(value = "根据文章id获取相应的标签集合", notes = "根据文章id获取相应的标签集合")
    @ApiImplicitParam(paramType = "path", name = "articleId", value = "文章id", required = true, dataType = "string")
    @GetMapping("/article/{articleId}")
    public List<ClassificationEntity> queryListByArticleId(@PathVariable(value = "articleId") String articleId) {
        return classificationService.queryListByArticleId(articleId);
    }

    @ApiOperation(value = "搜索文章分类", notes = "根据条件获取文章分类信息列表")
    @ApiImplicitParam(name = "classificationQueryForm", value = "文章分类查询参数", required = true, dataType = "ClassificationQueryForm")
    @PostMapping(value = "/list")
    public IPage queryList(@RequestBody ClassificationQueryForm classificationQueryForm) {
        log.info("classificationQueryForm:{}", classificationQueryForm);
        return classificationService.queryList(classificationQueryForm);
    }

    @ApiOperation(value = "搜索文章分类", notes = "获取所有文章分类信息列表")
    @GetMapping(value = "/all")
    public List<ClassificationEntity> getAll() {
        return classificationService.getAll();
    }

    @ApiOperation(value = "删除文章分类", notes = "根据id删除文章分类")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章分类id", required = true, dataType = "string")
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable(value = "id") String id) {
        return classificationService.remove(id);
    }
}

