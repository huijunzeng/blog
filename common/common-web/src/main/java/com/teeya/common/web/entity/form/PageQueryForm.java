package com.teeya.common.web.entity.form;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("分页封装表单")
@Data
public class PageQueryForm implements Serializable {

    @ApiModelProperty("页码")
    private int pageNum = 1;

    @ApiModelProperty("页大小")
    private int pageSize = 10;

    /**
     * 封装mybatisplus的Page类
     * @return
     */
    public Page getPage() {
        return new Page(this.getPageNum(), this.getPageSize());
    }
}
