package com.yy.eduservice.mapper;

import com.yy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.eduservice.entity.dto.CourseInfo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author ycr
 * @since 2020-06-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CourseInfo getCourseAllInfo(String id);

    int updateStatus(EduCourse eduCourse);
}
