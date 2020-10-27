package com.teeya.user.entity.form;

import com.teeya.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "用户查询表单")
@Data
public class UserQueryForm extends BaseQueryForm {

    @ApiModelProperty(value = "用户名", example = "admin")
    private String username;

    @ApiModelProperty(value = "手机号码", example = "12345678910")
    private String phone;

    @ApiModelProperty(value = "是否已删除 1已删除 0未删除", example = "1")
    private Integer deleted;

}
