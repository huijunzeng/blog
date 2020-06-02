package com.teeya.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.demo.entity.pojo.DictEntity;
import com.teeya.demo.mapper.DictMapper;
import com.teeya.demo.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DictMapper dictMapper;

    @Override
    public boolean createTable() {
        return dictMapper.createTableIfNotExists();
    }

    @Override
    public boolean save() {
        DictEntity dictEntity = new DictEntity();
        dictEntity.setUserId(20L);
        dictEntity.setName("性别");
        return super.save(dictEntity);
    }

    @Override
    public DictEntity get(Long id) {
        return super.getById(id);
    }
}
