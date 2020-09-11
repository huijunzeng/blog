package com.teeya.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.ClassificationSaveForm;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章分类表 前端控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 * 直接在方法内校验非实体类的单个参数时，需要加上@Validated注解（加@Valid无效）
 */
@RestController
@RequestMapping("/classification")
@Api(value = "classification", tags = {"文章分类操作restful接口"})
@Slf4j
@Validated
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;

    @ApiOperation(value = "新增文章分类", notes = "新增一个文章分类")
    @ApiImplicitParam(paramType = "form", name = "classificationForm", value = "新增文章分类表单", required = true, dataTypeClass = ClassificationSaveForm.class)
    @PostMapping
    public boolean save(@Validated @RequestBody ClassificationSaveForm classificationSaveForm) {
        return classificationService.save(classificationSaveForm);
    }

    @ApiOperation(value = "修改文章分类", notes = "修改文章分类")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "文章分类id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(paramType = "form", name = "labelUpdateForm", value = "文章分类修改表单", required = true, dataTypeClass = ClassificationUpdateForm.class)})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable Long id, @Validated @RequestBody ClassificationUpdateForm classificationUpdateForm) {
        return classificationService.update(id, classificationUpdateForm);
    }

    @ApiOperation(value = "获取文章分类", notes = "根据文章分类id获取指定文章分类信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章分类id", required = true, dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public ClassificationEntity get(@PathVariable Long id) {
        log.info("classificationId: " + id);
        return classificationService.get(id);
    }

    @ApiOperation(value = "根据文章id获取相应的标签集合", notes = "根据文章id获取相应的标签集合")
    @ApiImplicitParam(paramType = "path", name = "articleId", value = "文章id", required = true, dataTypeClass = Long.class)
    @GetMapping("/article/{articleId}")
    public List<ClassificationEntity> queryListByArticleId(@PathVariable Long articleId) {
        return classificationService.queryListByArticleId(articleId);
    }

    @ApiOperation(value = "搜索文章分类", notes = "根据条件获取文章分类信息列表")
    @ApiImplicitParam(paramType = "form", name = "classificationQueryForm", value = "文章分类查询表单", required = true, dataTypeClass = ClassificationQueryForm.class)
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
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章分类id", required = true, dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Long id) {
        return classificationService.remove(id);
    }
}

