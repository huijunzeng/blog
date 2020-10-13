package com.teeya.common.web.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "查询基础类")
public class BaseParam implements Serializable {

    @ApiModelProperty(value = "查询开始时间", example = "2019-11-24 23:11:18")
    private Date createdTimeStart;

    @ApiModelProperty(value = "查询结束时间", example = "2019-11-24 23:11:18")
    private Date createdTimeEnd;

}
