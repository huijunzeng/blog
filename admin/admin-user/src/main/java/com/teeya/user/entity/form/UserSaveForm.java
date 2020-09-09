package com.teeya.user.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "用户新增表单")
@Data
public class UserSaveForm implements Serializable {

    //@NotEmpty :不能为null，且Size>0
    //@NotNull:不能为null，但可以为empty,没有Size的约束
    //@NotBlank:只用于String,不能为null且trim()之后size>0   不能用于包装类型如Long
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty("密码密文")
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度在6到20个字符")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty("用户角色id集合")
    @NotNull
    private Set<Long> roleIds;

}
