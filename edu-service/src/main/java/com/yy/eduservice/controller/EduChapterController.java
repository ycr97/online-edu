package com.yy.eduservice.controller;


import com.yy.educommons.ResultCommon;
import com.yy.eduservice.entity.EduChapter;
import com.yy.eduservice.entity.dto.EduChapterDto;
import com.yy.eduservice.service.EduChapterService;
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
 * @since 2020-07-03
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Resource
    EduChapterService eduChapterService;

    @ApiOperation("得到章节视频嵌套结果")
    @GetMapping("/getByCourseId/{courseId}")
    public ResultCommon listChapterByCourseId(
            @ApiParam(name = "courseId", value = "课程Id", required = true)
            @PathVariable String courseId){

        List<EduChapterDto> eduChapterDtos = eduChapterService.nestedList(courseId);
        Map<String, List<EduChapterDto>> map = new HashMap<>();
        map.put("nested", eduChapterDtos);

        return ResultCommon.success(map);
    }

    @ApiOperation("根据Id得到章节信息")
    @GetMapping("/{id}")
    public ResultCommon getChapterById(
            @ApiParam(name = "id", value = "章节Id", required = true)
            @PathVariable String id) {

        EduChapter eduChapter = eduChapterService.getById(id);

        return ResultCommon.success(eduChapter);
    }

    @ApiOperation("删除章节")
    @DeleteMapping("/{id}")
    public ResultCommon deleteById(
            @ApiParam(name = "id", value = "课程Id", required = true)
            @PathVariable String id){
        boolean flag = eduChapterService.removeChapterById(id);
        if (flag) {
            return ResultCommon.success();
        } else {
            return ResultCommon.fail();
        }
    }

    @ApiOperation("更新章节")
    @PutMapping
    public ResultCommon updateById(
            @ApiParam(name = "eduChapter", value = "章节对象", required = true)
            @RequestBody EduChapter eduChapter) {


        boolean b = eduChapterService.updateById(eduChapter);
        if (b) {
            return ResultCommon.success();
        } else {
            return ResultCommon.fail();
        }
    }

    @ApiOperation("添加章节")
    @PostMapping
    public ResultCommon saveChapter(
            @ApiParam(name = "eduChapter", value = "章节", required = true)
            @RequestBody EduChapter eduChapter) {

        boolean save = eduChapterService.save(eduChapter);
        if (save) {
            return ResultCommon.success();
        }else{
            return ResultCommon.fail();
        }
    }
}

