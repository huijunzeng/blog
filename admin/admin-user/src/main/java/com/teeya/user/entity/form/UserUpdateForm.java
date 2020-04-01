package com.teeya.user.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "用户更新表单")
@Data
public class UserUpdateForm implements Serializable {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码密文")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phone;
}
