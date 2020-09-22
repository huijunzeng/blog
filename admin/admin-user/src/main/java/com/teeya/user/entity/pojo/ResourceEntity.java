package com.teeya.user.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.web.entity.pojo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator 2019/11/21
*/
@TableName(value = "resource")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceEntity extends BaseEntity {

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型(0菜单 1按钮)
     */
    private String type;

    /**
     * 资源url
     */
    private String url;

    /**
     * 资源请求方法
     */
    private String method;

}