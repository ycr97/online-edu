package com.yy.eduservice.service;

import com.yy.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-06-28
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {
    boolean removeByCourseId(String id);
}
