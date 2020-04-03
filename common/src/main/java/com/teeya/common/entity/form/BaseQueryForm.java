package com.teeya.common.entity.form;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teeya.common.entity.pojo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ZJH
 * @Date: 2019/8/8 19:08
 */

@ApiModel(value = "基础查询表单")
@Data
@Slf4j
public class BaseQueryForm<T extends BaseEntity> extends PageQueryForm implements Serializable {

    @ApiModelProperty("查询开始时间")
    private Date createdTimeStart;

    @ApiModelProperty("查询结束时间")
    private Date createdTimeEnd;

    // 封装时间查询包装器方法，避免重复代码
    public QueryWrapper<T> build() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(null != this.createdTimeStart, T :: getCreatedTime, this.createdTimeStart)
                .le(null != this.createdTimeEnd, T :: getCreatedTime, this.createdTimeEnd);
        log.info("QueryWrapper : " + queryWrapper.toString());
        return queryWrapper;
    }
}
