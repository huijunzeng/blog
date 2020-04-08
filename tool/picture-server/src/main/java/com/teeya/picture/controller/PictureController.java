package com.teeya.picture.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.teeya.picture.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author ZJH
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/picture")
@Api(value = "picture", tags = {"图片操作restful接口"})
@Slf4j
public class PictureController {

    @Autowired
    private PictureService pictureService;

    // 读取配置文件中七牛云的公私钥以及存储空间名
    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @ApiImplicitParam(name = "upload", value = "上传图片", required = true, dataType = "ArticleForm")
    @PostMapping
    public boolean save(@RequestParam("file") MultipartFile file) {
        // todo 加水印？
        Configuration configuration = new Configuration(Region.region2());
        UploadManager manager = new UploadManager(configuration);

        String key = "test.png";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        String localFilePath = "E:\\fileSrv\\1584675142.jpg";

        Response response = null;
        try {
            response = manager.put(localFilePath, key, upToken);
            DefaultPutRet set = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info(set.key);
            return true;
        } catch (QiniuException e) {
            Response r = e.response;
            log.error("response: {}", r);
            return false;
        }
    }

    // demo
    public static void main(String[] args) {
        String accessKey = "iNffcCO5SH8qw-GgV9L_QGbTH2_xLgEVdZIsq6nW";
        String secretKey = "NSNHLflVqXkOM86sEvxbTew3LEX2fCUf-ifQU5TI";
        String bucket = "huijunzeng";

        Configuration configuration = new Configuration(Region.region2());
        UploadManager manager = new UploadManager(configuration);

        String key = "test.png";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        String localFilePath = "E:\\fileSrv\\1584675142.jpg";


        Response response = null;
        try {
            response = manager.put(localFilePath, key, upToken);
            DefaultPutRet set = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(set.key);
            System.out.println(set.hash);

        } catch (QiniuException e) {
            Response r = e.response;
            System.err.println(r.toString());
        }
    }
}
