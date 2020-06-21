package com.yy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.eduservice.entity.EduTeacherQO;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-05-30
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void conditionList(Page<EduTeacher> page, EduTeacherQO qo);
}
