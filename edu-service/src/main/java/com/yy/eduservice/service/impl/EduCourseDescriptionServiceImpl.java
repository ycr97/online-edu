package com.yy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.eduservice.entity.EduCourseDescription;
import com.yy.eduservice.mapper.EduCourseDescriptionMapper;
import com.yy.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-06-28
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public boolean removeByCourseId(String id) {

        QueryWrapper<EduCourseDescription> qw = new QueryWrapper<>();
        qw.eq("id", id);
        int delete = baseMapper.delete(qw);
        return delete > 0;
    }
}
