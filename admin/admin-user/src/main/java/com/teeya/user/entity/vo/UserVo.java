package com.teeya.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "用户vo实体类")
@Data
public class UserVo implements Serializable {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新时间")
    private Date updatedTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("用户角色id集合")
    private Set<String> roleIds;
}
