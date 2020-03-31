package com.teeya.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.teeya.common.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 实体类自动填充处理类
 * mybatisplus没有类似于tk.mybatis的insertSelective方法，所以在insert时，对于没有赋值的字段会做null插入，因此数据库表结构的默认字段不会生效
 * 我们可以通过实现接口MetaObjectHandle，对实体类的某些公共字段进行自动赋值
 * @Author: ZJH
 * @Date: 2020/3/11 13:18
 */

@Slf4j
public class BaseEntityMetaObjectHandler implements MetaObjectHandler {

    /**
     * 获取当前交易的用户，为空返回默认system
     *
     * @return
     */
    private String getCurrentUsername() {
        return StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUsername(), null);
    }

    /**
     * 在执行mybatisPlus的insert()时，为某些公共字段填充值
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdBy", getCurrentUsername(), metaObject);
        this.setFieldValByName("updatedBy", getCurrentUsername(), metaObject);
        this.setFieldValByName("createdTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
        this.setFieldValByName("updatedTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
        this.setFieldValByName("deleted", 0, metaObject);
        this.setFieldValByName("version", 0, metaObject);
    }

    /**
     * 在执行mybatisPlus的update()时，为某些公共字段填充值
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedBy", getCurrentUsername(), metaObject);
        this.setFieldValByName("updatedTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
    }
}