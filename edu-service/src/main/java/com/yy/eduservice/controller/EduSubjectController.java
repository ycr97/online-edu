package com.yy.eduservice.controller;


import com.yy.educommons.ResultCommon;
import com.yy.eduservice.entity.vo.SubjectNestedVO;
import com.yy.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ycr
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/eduservice/subject")
@Api(description="课程分类管理")
@CrossOrigin //跨域
public class EduSubjectController {

    @Resource
    EduSubjectService eduSubjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("/import")
    public ResultCommon upload(
            @ApiParam(name = "file", value = "Excel文件",required = true)
                    @RequestParam(value = "file", required = true)
                    MultipartFile file){

        List<String> list = eduSubjectService.importSubject(file);
        if (list.size() == 0) {
            return ResultCommon.success();
        }else {
            Map<String, List<String>> map = new HashMap<>();
            map.put("msgList", list);
            return ResultCommon.success(map);
        }

    }

    @ApiOperation(value = "分类列表")
    @GetMapping("/list")
    public ResultCommon nestedList(){
        List<SubjectNestedVO> subjectNestedVOS = eduSubjectService.nestedList();

        Map<String, List<SubjectNestedVO>> map = new HashMap<>();
        map.put("nestedList", subjectNestedVOS);
        return ResultCommon.success(map);

    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping("/{id}")
    public ResultCommon removeSubject(
            @ApiParam(name = "id", value = "分类id", required = true)
            @PathVariable("id") String id){
        boolean b = eduSubjectService.removeById(id);
        if (b) {
            return ResultCommon.success();
        }else {
            return ResultCommon.builder().message("删除失败").code(20001).isOk(false).build();
        }
    }

}

