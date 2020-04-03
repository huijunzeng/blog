package com.teeya.article.entity.form;

import com.teeya.article.entity.pojo.ClassificationEntity;
import com.teeya.common.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: ZJH
 * @Date: 2020/3/18 18:03
 */

@ApiModel(value = "文章分类查询表单")
@Data
public class ClassificationQueryForm extends BaseQueryForm<ClassificationEntity> {

    @ApiModelProperty("分类名称")
    private String classificationName;
}
