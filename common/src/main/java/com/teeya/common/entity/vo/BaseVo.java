package com.teeya.common.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "vo基础类")
public class BaseVo implements Serializable {

    private String id;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;
}
