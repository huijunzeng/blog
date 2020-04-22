package com.teeya.file.service;

import com.teeya.file.entity.vo.UploadResultVo;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    /**
     * 图片上传
     * @param file
     * @return
     */
    UploadResultVo imgUpload(MultipartFile file);
}
