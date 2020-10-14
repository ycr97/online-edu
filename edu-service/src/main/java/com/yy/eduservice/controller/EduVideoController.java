package com.yy.eduservice.controller;


import com.yy.educommons.ResultCommon;
import com.yy.eduservice.entity.EduVideo;
import com.yy.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ycr
 * @since 2020-07-03
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Resource
    EduVideoService eduVideoService;

    @PostMapping
    @ApiOperation("添加小节")
    public ResultCommon saveVideo(
            @ApiParam(name = "eduVideo", value = "小节信息", required = true)
            @RequestBody EduVideo eduVideo) {

        boolean save = eduVideoService.save(eduVideo);
        if (save) {
            return ResultCommon.success();
        } else {
            return ResultCommon.fail();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除小节")
    public ResultCommon deleteById(
            @ApiParam(name = "id", value = "小节Id", required = true)
            @PathVariable String id) {

        boolean b = eduVideoService.deleteBarById(id);
        if (b) {
            return ResultCommon.success();
        } else {
            return ResultCommon.fail();
        }
    }

    @PutMapping
    @ApiOperation("修改小节信息")
    public ResultCommon update(
            @ApiParam(name = "eduVideo", value = "小节对象", required = true)
            @RequestBody EduVideo eduVideo) {
        boolean b = eduVideoService.updateById(eduVideo);
        if (b) {
            return ResultCommon.success();
        } else {
            return ResultCommon.fail();
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("根据Id获取小节对象")
    public ResultCommon getById(
            @ApiParam(name = "id", value = "小节Id", required = true)
            @PathVariable String id) {
        EduVideo eduVideo = eduVideoService.getById(id);
        return ResultCommon.success(eduVideo);

    }


}

