package com.teeya.file.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/4/22 9:46
 */

@ApiModel(value = "上传结果实体类")
@Data
@NoArgsConstructor
public class UploadResultVo implements Serializable {

    @ApiModelProperty("文件服务器文件url")
    private String url;

    @ApiModelProperty("状态 success/fail")
    private String status;

    @ApiModelProperty("信息")
    private String message;
}
