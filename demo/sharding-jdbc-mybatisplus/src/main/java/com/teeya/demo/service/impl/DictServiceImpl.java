package com.teeya.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.demo.entity.pojo.DictEntity;
import com.teeya.demo.entity.pojo.OrderEntity;
import com.teeya.demo.mapper.DictMapper;
import com.teeya.demo.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
@Service
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements DictService {

    @Override
    public boolean save() {
        return true;
    }
}
