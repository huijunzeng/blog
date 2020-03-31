package com.teeya.article.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.teeya.common.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章标签表
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("label")
public class LabelEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 标签名称
     */
    @TableField("label_name")
    private String labelName;

    public static void main(String[] args) {
        String accessKey = "iNffcCO5SH8qw-GgV9L_QGbTH2_xLgEVdZIsq6nW";
        String secretKey = "NSNHLflVqXkOM86sEvxbTew3LEX2fCUf-ifQU5TI";
        String bucket = "huijunzeng";

        Configuration configuration = new Configuration(Zone.zone2());
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
