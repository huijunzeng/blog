package com.teeya.common.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "查询基础类")
public class BaseParam implements Serializable {

    @ApiModelProperty("查询开始时间")
    private Date createdTimeStart;

    @ApiModelProperty("查询结束时间")
    private Date createdTimeEnd;

}
