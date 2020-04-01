package com.teeya.common.entity.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "form基础类")
public class BaseForm implements Serializable {

    private String username;

}
