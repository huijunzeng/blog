package com.teeya.file.controller;

import com.teeya.file.entity.vo.UploadResultVo;
import com.teeya.file.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

/**
 * @author ZJH
 * @since 2020-04-05
 * 直接在方法内校验非实体类的单个参数时，需要加上@Validated注解（加@Valid无效）
 */
@RestController
@RequestMapping("/upload")
@Api(value = "upload", tags = {"文件上传操作restful接口"})
@Slf4j
@Validated
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "图片上传", notes = "图片上传")
    @ApiImplicitParam(paramType = "query", name = "file", value = "图片上传文件", required = true, dataTypeClass = MultipartFile.class)
    @PostMapping("/img")
    public UploadResultVo imgUpload(@NotBlank(message = "上传文件不能为空") @RequestParam("file") MultipartFile file) {
        return uploadService.imgUpload(file);
    }

}
