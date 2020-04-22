package com.teeya.article.entity.form;

import com.teeya.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "文章标签查询表单")
@Data
public class LabelQueryForm extends BaseQueryForm {

    @ApiModelProperty("标签名称")
    private String name;

}
