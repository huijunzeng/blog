package com.teeya.authorization.entity;

import com.teeya.common.web.entity.pojo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码密文
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

}