package com.teeya.picture.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.teeya.picture.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    // 引入QiniuConfig配置类中的Bean类Auth和Configuration
    @Autowired
    private Auth auth;
    @Autowired
    private Configuration configuration;

    /**
     * 读取配置文件中的存储空间名称以及外链域名
     */
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.path}")
    private String path;

    @Override
    public String imgUpload(MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(fileBytes);
        // 文件名
        String originalFilename = file.getOriginalFilename();
        log.info("originalFilename: {}", originalFilename);
        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        log.info("suffix: {}", suffix);
        UploadManager uploadManager = new UploadManager(configuration);
        // 获取一个随机的文件名
        String filename = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        log.info("new filename: {}", filename);
        // 获取七牛云提供的 token
        String upToken = auth.uploadToken(bucket);

        // 七牛云用来获取返回信息的相应
        Response response;
        // 用来获取上传后的图片地址
        String picAddr = null;
        try {
            response = uploadManager.put(byteInputStream, filename, upToken, null, null);
            // 返回的 response其实是一个 json，转换为 Map，然后其中的 key就是上传的文件名了，其实就是上面生产的 filename
            // 由于域名还未备案完成，所以还不能真正得到图片直链访问地址，这里只是得到图片的文件名
            picAddr = new Gson().fromJson(response.bodyString(), Map.class).get("key").toString();
            log.info("response json: {}", new Gson().fromJson(response.bodyString(), Map.class));
        } catch (QiniuException e) {
            response = e.response;
            log.error("【上传服务】上传图片发生出错误！{}", response.toString());
        }

        // 这里是前端使用的 editor.md，要求上传图片后的返回格式
        Map<String, Object> result = new HashMap<String, Object>(3) {{
            put("url", "picAddr");
            put("success", 1);
            put("message", "upload success!");
        }};

        return picAddr;
    }
}
