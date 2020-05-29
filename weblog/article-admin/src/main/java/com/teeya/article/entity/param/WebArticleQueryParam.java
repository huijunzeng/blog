package com.teeya.article.entity.param;

import com.teeya.common.web.entity.param.PageQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "前台页面文章查询表单")
@Data
public class WebArticleQueryParam extends PageQueryParam {

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("标签id")
    private Long labelId;

    @ApiModelProperty("分类id")
    private Long classificationId;

}
