package com.teeya.authentication.entity;

import com.teeya.common.web.entity.pojo.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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