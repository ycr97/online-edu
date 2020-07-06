package com.yy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.eduservice.entity.EduTeacher;
import com.yy.eduservice.entity.qo.EduTeacherQO;
import com.yy.eduservice.mapper.EduTeacherMapper;
import com.yy.eduservice.service.EduTeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-05-30
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    public void conditionList(Page<EduTeacher> page, EduTeacherQO qo) {

        if (qo == null){
            baseMapper.selectPage(page, null);
            return;
        }
        String name = qo.getName();
        String level = qo.getLevel();
        String end = qo.getEnd();
        String begin = qo.getBegin();

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(page, wrapper);
    }
}
