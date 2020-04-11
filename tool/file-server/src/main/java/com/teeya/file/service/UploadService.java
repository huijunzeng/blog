package com.teeya.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    /**
     * 图片上传
     * @param file
     * @return
     */
    String imgUpload(MultipartFile file) throws IOException;
}
