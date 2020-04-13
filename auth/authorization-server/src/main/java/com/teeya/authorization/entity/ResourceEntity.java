package com.teeya.authorization.entity;

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
     * 资源类型
     */
    private String type;

    /**
     * 资源url
     */
    private String url;

}