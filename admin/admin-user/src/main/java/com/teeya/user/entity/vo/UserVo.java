package com.teeya.user.entity.vo;

import com.teeya.common.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "用户vo实体类")
@Data
@NoArgsConstructor
public class UserVo extends BaseVo {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phone;
}
