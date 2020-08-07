package com.yy.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.educommons.ResultCommon;
import com.yy.eduservice.entity.EduCourse;
import com.yy.eduservice.entity.EduTeacher;
import com.yy.eduservice.service.EduCourseService;
import com.yy.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycr
 * @date 2020/7/21
 */
@RestController
@RequestMapping("/eduservice/index")
@CrossOrigin
public class IndexController {

    @Resource
    private EduCourseService eduCourseService;

    @Resource
    private EduTeacherService eduTeacherService;

    @GetMapping
    @ApiOperation("查询前8条热门课程,查询前4位名师")
    public ResultCommon index() {
        // 查询前8条热门课程,根据观看人数来
        QueryWrapper<EduCourse> courseW = new QueryWrapper<>();
        courseW.orderByDesc("view_count");
        courseW.last("limit 8");
        List<EduCourse> courses = eduCourseService.list(courseW);
        // 查询前4位讲师
        QueryWrapper<EduTeacher> teacherW = new QueryWrapper<>();
        teacherW.orderByDesc("id");
        teacherW.last("limit 4");
        List<EduTeacher> teachers = eduTeacherService.list(teacherW);
        Map<String, Object> map = new HashMap<>();
        map.put("courses", courses);
        map.put("teachers", teachers);
        return ResultCommon.success(map);
    }
}
