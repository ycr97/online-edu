package com.yy.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.educommons.ResultCommon;
import com.yy.eduservice.entity.EduTeacher;
import com.yy.eduservice.entity.EduTeacherQO;
import com.yy.eduservice.service.EduTeacherService;
import com.yy.exception.CustomExceptionType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ycr
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Resource
    private EduTeacherService eduTeacherService;

    //{"code":20000,"data":{"token":"admin-token"}}
    //{"code":20000,"data":{"roles":["admin"],"introduction":"I am a super administrator",
    // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif","name":"Super Admin"}}


    @PostMapping("/login")
    public ResultCommon login(){
        Map<String, String> map = new HashMap<>();
        map.put("token", "admin-token");
        return ResultCommon.builder()
                .code(20000)
                .data(map)
                .build();
    }

    @GetMapping("/info")
    public ResultCommon loginInfo(){
        Map<String, String> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super Admin");
        return ResultCommon.builder()
                .code(20000)
                .data(map)
                .build();
    }

    /**
     * 获取所有的教师
     * @return list
     */
    @GetMapping("/list")
    public ResultCommon listAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return ResultCommon.success(list);
    }

    @DeleteMapping("/{id}")
    public ResultCommon deleteById(@PathVariable("id") String id){
        boolean b = eduTeacherService.removeById(id);
        if (b){
            return ResultCommon.success();
        }else {
            return ResultCommon.error(CustomExceptionType.OTHER_ERROR, "删除失败");
        }
    }

    /**
     * 分页查询所有
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public ResultCommon getPageList(@PathVariable Integer page, @PathVariable Integer limit){
        Page<EduTeacher> eduTeacherPage = new Page<>(page, limit);

        eduTeacherService.page(eduTeacherPage, null);

        List<EduTeacher> records = eduTeacherPage.getRecords();
        long total = eduTeacherPage.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("data", records);
        map.put("total", total);
        return ResultCommon.success(map);
    }

    /**
     * 条件查询
     * @param page 起始页
     * @param limit 每页显示多少数据
     * @param qo 查询条件对象
     * @return resultCommons
     */
    @PostMapping("/conditionList/{page}/{limit}")
    public ResultCommon getPageListForCondition(@PathVariable Integer page, @PathVariable Integer limit,
                                                EduTeacherQO qo){
        Page<EduTeacher> eduTeacherPage = new Page<>(page, limit);

        eduTeacherService.conditionList(eduTeacherPage, qo);

        List<EduTeacher> records = eduTeacherPage.getRecords();
        long total = eduTeacherPage.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("data", records);
        map.put("total", total);
        return ResultCommon.success(map);
    }

    @ApiOperation(value = "根据Id修改讲师")
    @PutMapping("/{id}")
    public ResultCommon updateTeacher(
            @ApiParam(name = "id", value = "讲师Id", required = true)
            @PathVariable("id") String id ,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody(required = false) EduTeacher eduTeacher){

        eduTeacher.setId(id);
        eduTeacherService.updateById(eduTeacher);
        return ResultCommon.success();
    }

    @ApiOperation(value = "新增老师")
    @PostMapping
    public ResultCommon save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        eduTeacherService.save(teacher);

        return ResultCommon.success();
    }

    @ApiOperation(value = "根据Id查询讲师")
    @GetMapping("/{id}")
    public ResultCommon getTeacherById(
            @ApiParam(name = "id", value = "讲师id", required = true)
            @PathVariable String id
    ){
        EduTeacher e = eduTeacherService.getById(id);
        return ResultCommon.success(e);
    }
}

