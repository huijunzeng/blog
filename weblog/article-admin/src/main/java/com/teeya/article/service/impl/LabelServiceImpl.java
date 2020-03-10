package com.teeya.article.service.impl;

import com.teeya.article.entity.pojo.LabelEntity;
import com.teeya.article.mapper.LabelMapper;
import com.teeya.article.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签表 服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Service
@Slf4j
public class LabelServiceImpl extends ServiceImpl<LabelMapper, LabelEntity> implements LabelService {

}
