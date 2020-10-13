package com.teeya.common.web.entity.form;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("分页封装表单")
@Data
public class PageQueryForm implements Serializable {

    @ApiModelProperty(value = "页码", example = "1")
    private int pageNum = 1;

    @ApiModelProperty(value = "页大小", example = "10")
    private int pageSize = 10;

    /**
     * 封装mybatisplus的Page类
     * @return
     */
    public Page getPage() {
        return new Page(this.getPageNum(), this.getPageSize());
    }
}
