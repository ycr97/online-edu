package com.yy.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.educommons.ResultCommon;
import com.yy.eduservice.entity.EduCourse;
import com.yy.eduservice.entity.dto.CourseInfo;
import com.yy.eduservice.entity.dto.CourseInfoForm;
import com.yy.eduservice.entity.qo.CourseQO;
import com.yy.eduservice.service.EduCourseService;
import com.yy.exception.CustomException;
import com.yy.exception.CustomExceptionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ycr
 * @since 2020-06-24
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Resource
    private EduCourseService eduCourseService;

    /**
     * 保存课程信息
     * @param courseInfoForm
     * @return
     */
    @PostMapping("/saveCourse")
    @ApiOperation("新建课程信息")
    public ResultCommon addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){

        String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
        if (courseId != null) {
            return ResultCommon.success(courseId);
        }else {
            return ResultCommon.builder().code(20001).isOk(false).message("发布课程失败").build();
        }
    }

    /**
     * 根据Id查询课程信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据Id得到课程信息")
    public ResultCommon getCourseById(@PathVariable
                    @ApiParam(value = "id", name = "id", required = true)
                    String id) {
        CourseInfoForm course = eduCourseService.getCourseInfoById(id);
        return ResultCommon.success(course);

    }

    @PutMapping("/{id}")
    @ApiOperation("修改课程信息")
    public ResultCommon updateCourse(
            @ApiParam(name = "courseInfoForm", value = "dto对象", required = true)
            @PathVariable String id,
            @ApiParam(name = "courseInfoForm", value = "dto对象", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {

        boolean b = eduCourseService.updateCourseById(id, courseInfoForm);
        if (b) {
            return ResultCommon.success();
        }else {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "修改失败");
        }
    }

    @ApiOperation("修改课程信息")
    @GetMapping("/{page}/{limit}")
    public ResultCommon getCourseByPage(
            @ApiParam(name = "page", value = "起始页", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "一页显示多少条数")
            @PathVariable Integer limit) {

        Page<EduCourse> eduCoursePage = new Page<>(page, limit);

        eduCourseService.page(eduCoursePage, null);

        // 总条数
        long total = eduCoursePage.getTotal();
        // 需要页数的记录
        List<EduCourse> records = eduCoursePage.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("data", records);
        map.put("total", total);

        return ResultCommon.success(map);
    }

    @ApiOperation("修改课程信息")
    @PostMapping("/condition/{page}/{limit}")
    public ResultCommon getCourseByPage(
            @ApiParam(name = "page", value = "起始页", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "一页显示多少条数", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "courseQO", value = "条件")
            @RequestBody CourseQO courseQO) {

        Page<EduCourse> eduCoursePage = new Page<>(page, limit);
        eduCourseService.conditionList(eduCoursePage, courseQO);
        long total = eduCoursePage.getTotal();
        List<EduCourse> records = eduCoursePage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", records);
        return ResultCommon.success(map);

    }

    @ApiOperation("删除课程信息")
    @DeleteMapping("/{id}")
    public ResultCommon removeCourseById(
            @ApiParam(name = "id", value = "课程id", required = true)
            @PathVariable String id) {

        boolean b = eduCourseService.deleteCourseById(id);
        if (b) {
            return ResultCommon.success();
        }else {
            return ResultCommon.fail();
        }
    }

    @ApiOperation("发布课程前核查信息")
    @GetMapping("/getBasicInfo/{id}")
    public ResultCommon getCourseAllInfo(
            @ApiParam(name = "id", value = "课程Id", required = true)
            @PathVariable String  id) {
        CourseInfo basicInfo = eduCourseService.getBasicInfo(id);
        if (basicInfo != null) {
            return ResultCommon.success(basicInfo);
        }else {
            return ResultCommon.fail();
        }
    }

    @ApiOperation("课程最终发布")
    @PutMapping("/publish/{id}")
    public ResultCommon updateCourseStatus(
            @ApiParam(name = "id", value = "课程Id", required = true)
             @PathVariable String  id) {
        boolean b = eduCourseService.updateCourseStatus(id);
        if (b) {
            return ResultCommon.success();
        }else {
            return ResultCommon.fail();
        }
    }

}

