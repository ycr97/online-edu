package com.yy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.eduservice.entity.EduChapter;
import com.yy.eduservice.entity.EduCourse;
import com.yy.eduservice.entity.EduCourseDescription;
import com.yy.eduservice.entity.dto.CourseInfoForm;
import com.yy.eduservice.entity.qo.CourseQO;
import com.yy.eduservice.mapper.EduCourseMapper;
import com.yy.eduservice.service.EduChapterService;
import com.yy.eduservice.service.EduCourseDescriptionService;
import com.yy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.eduservice.service.EduVideoService;
import com.yy.exception.CustomException;
import com.yy.exception.CustomExceptionType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-06-24
 */
@Service
@Transactional
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Resource
    private EduCourseDescriptionService descriptionService;

    @Resource
    private EduChapterService eduChapterService;

    @Resource
    private EduVideoService eduVideoService;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        // 1.添加到课程信息表
        // 由于使用的是baseMapper需要将CourseInfoForm对象拷贝到EduCourse对象中
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        // 调用mapper接口
        int result = baseMapper.insert(eduCourse);
        if (result < 0) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "发布课程失败");
        }

        // 2.添加到课程信息描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseInfoForm.getDescription());
        description.setId(eduCourse.getId());

        boolean b = descriptionService.save(description);

        if (b) {
            return eduCourse.getId();
        }else {
            return null;
        }
    }

    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        if (eduCourse == null) {
            throw new CustomException(CustomExceptionType.OTHER_ERROR, "没有课程信息");
        }
        EduCourseDescription d = descriptionService.getById(id);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse, courseInfoForm);
        courseInfoForm.setDescription(d.getDescription());
        return courseInfoForm;
    }

    @Override
    public boolean updateCourseById(String id, CourseInfoForm courseInfoForm) {

        // 修改课程信息表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        eduCourse.setId(id);
        int result = baseMapper.updateById(eduCourse);
        if (result == 0) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "修改课程失败");
        }

        // 修改课程描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(id);
        description.setDescription(courseInfoForm.getDescription());
        return descriptionService.updateById(description);
    }

    @Override
    public void conditionList(Page<EduCourse> page, CourseQO qo) {
        if (qo == null) {
            baseMapper.selectPage(page, null);
            return;
        }
        QueryWrapper<EduCourse> qw = new QueryWrapper<>();

        String subjectId = qo.getSubjectId();
        String subjectParentId = qo.getSubjectParentId();
        String teacherId = qo.getTeacherId();
        String title = qo.getTitle();

        if (!StringUtils.isEmpty(subjectId)) {
            qw.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            qw.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            qw.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(title)) {
            qw.like("title", title);
        }

        baseMapper.selectPage(page, qw);
    }

    @Override
    @Transactional
    public boolean deleteCourseById(String id) {
        //根据courseId删除小节数据
        boolean video = eduVideoService.deleteByCourseId(id);

        //根据courseId删除章节数据
        boolean chapter = eduChapterService.removeByCourseId(id);

        //根据courseId删除课程描述
        boolean b = descriptionService.removeByCourseId(id);

        //删除Course表中数据
        int i = baseMapper.deleteById(id);

        return i > 0;
    }

}
