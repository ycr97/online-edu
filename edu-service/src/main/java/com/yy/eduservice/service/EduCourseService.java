package com.yy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.eduservice.entity.dto.CourseInfo;
import com.yy.eduservice.entity.dto.CourseInfoForm;
import com.yy.eduservice.entity.qo.CourseQO;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-06-24
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    boolean updateCourseById(String id, CourseInfoForm courseInfoForm);

    void conditionList(Page<EduCourse> page, CourseQO qo);

    boolean deleteCourseById(String id);

    CourseInfo getBasicInfo(String id);

    boolean updateCourseStatus(String id);

}
