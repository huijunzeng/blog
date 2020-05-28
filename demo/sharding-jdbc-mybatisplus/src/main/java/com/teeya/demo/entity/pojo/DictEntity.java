package com.teeya.demo.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_dict")
public class DictEntity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

}
