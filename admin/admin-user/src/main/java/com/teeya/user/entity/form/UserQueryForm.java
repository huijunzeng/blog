package com.teeya.user.entity.form;

import com.teeya.common.web.entity.form.BaseQueryForm;
import com.teeya.common.web.entity.form.PageQueryForm;
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

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("是否已删除 1已删除 0未删除")
    private Integer deleted;

}
