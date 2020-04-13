package com.teeya.file.controller;

import com.teeya.file.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ZJH
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/upload")
@Api(value = "upload", tags = {"文件上传操作restful接口"})
@Slf4j
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "图片上传", notes = "图片上传")
    @ApiImplicitParam(paramType = "query", name = "file", value = "图片上传文件", required = true, dataType = "MultipartFile")
    @PostMapping("/img")
    public String imgUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadService.imgUpload(file);
    }

}
