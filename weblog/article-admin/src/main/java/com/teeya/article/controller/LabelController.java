package com.teeya.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.LabelForm;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiImplicitParam(name = "labelForm", value = "文章标签新增表单", required = true, dataType = "LabelForm")
    @PostMapping
    public boolean insert(@RequestBody LabelForm labelForm) {
        return labelService.insert(labelForm);
    }

    @ApiOperation(value = "修改文章标签", notes = "修改文章标签")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "文章标签id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "labelUpdateForm", value = "文章标签修改表单", required = true, dataType = "LabelUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable String id, @RequestBody LabelUpdateForm labelUpdateForm) {
        return labelService.update(id, labelUpdateForm);
    }

    @ApiOperation(value = "获取文章标签", notes = "根据文章标签id获取指定文章标签信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章标签id", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public LabelEntity get(@PathVariable String id) {
        log.info("labelId: " + id);
        return labelService.get(id);
    }

    @ApiOperation(value = "根据文章id获取相应的标签集合", notes = "根据文章id获取相应的标签集合")
    @ApiImplicitParam(paramType = "path", name = "articleId", value = "文章id", required = true, dataType = "string")
    @GetMapping("/article/{articleId}")
    public List<LabelEntity> query(@PathVariable(value = "articleId") String articleId) {
        return labelService.queryListByArticleId(articleId);
    }
    
    @ApiOperation(value = "搜索文章标签", notes = "根据条件获取文章标签信息列表")
    @ApiImplicitParam(name = "userQueryForm", value = "文章标签查询参数", required = true, dataType = "UserQueryForm")
    @PostMapping(value = "/list")
    public IPage query(@RequestBody LabelQueryForm labelQueryForm) {
        log.info("labelQueryForm:{}", labelQueryForm);
        return labelService.queryList(labelQueryForm);
    }

    @ApiOperation(value = "搜索文章标签", notes = "获取所有文章标签信息列表")
    @GetMapping(value = "/all")
    public List<LabelEntity> get() {
        return labelService.getAll();
    }
    
}

