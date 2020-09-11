package com.teeya.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.LabelSaveForm;
import com.teeya.article.entity.form.LabelQueryForm;
import com.teeya.article.entity.form.LabelUpdateForm;
import com.teeya.article.entity.pojo.LabelEntity;
import com.teeya.article.service.LabelService;
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
 * 文章标签表 前端控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 * 直接在方法内校验非实体类的单个参数时，需要加上@Validated注解（加@Valid无效）
 */
@RestController
@RequestMapping("/label")
@Api(value = "label", tags = {"文章标签操作restful接口"})
@Slf4j
@Validated
public class LabelController {

    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "新增文章标签", notes = "新增一个文章标签")
    @ApiImplicitParam(paramType = "form", name = "labelForm", value = "文章标签新增表单", required = true, dataTypeClass = LabelSaveForm.class)
    @PostMapping
    public boolean save(@Validated @RequestBody LabelSaveForm labelSaveForm) {
        return labelService.save(labelSaveForm);
    }

    @ApiOperation(value = "修改文章标签", notes = "修改文章标签")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "文章标签id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(paramType = "form", name = "labelUpdateForm", value = "文章标签修改表单", required = true, dataTypeClass = LabelUpdateForm.class)})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable Long id, @Validated @RequestBody LabelUpdateForm labelUpdateForm) {
        return labelService.update(id, labelUpdateForm);
    }

    @ApiOperation(value = "获取文章标签", notes = "根据文章标签id获取指定文章标签信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章标签id", required = true, dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public LabelEntity get(@PathVariable Long id) {
        log.info("labelId: " + id);
        return labelService.get(id);
    }

    @ApiOperation(value = "根据文章id获取相应的标签集合", notes = "根据文章id获取相应的标签集合")
    @ApiImplicitParam(paramType = "path", name = "articleId", value = "文章id", required = true, dataTypeClass = Long.class)
    @GetMapping("/article/{articleId}")
    public List<LabelEntity> queryListByArticleId(@PathVariable Long articleId) {
        return labelService.queryListByArticleId(articleId);
    }
    
    @ApiOperation(value = "搜索文章标签", notes = "根据条件获取文章标签信息列表")
    @ApiImplicitParam(paramType = "form", name = "labelQueryForm", value = "文章标签查询表单", required = true, dataTypeClass = LabelQueryForm.class)
    @PostMapping(value = "/list")
    public IPage queryList(@RequestBody LabelQueryForm labelQueryForm) {
        log.info("labelQueryForm:{}", labelQueryForm);
        return labelService.queryList(labelQueryForm);
    }

    @ApiOperation(value = "搜索文章标签", notes = "获取所有文章标签信息列表")
    @GetMapping(value = "/all")
    public List<LabelEntity> getAll() {
        return labelService.getAll();
    }

    @ApiOperation(value = "删除文章标签", notes = "根据id删除文章标签")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章标签id", required = true, dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Long id) {
        return labelService.remove(id);
    }
}

