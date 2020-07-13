package com.yy.eduvideoservice.controller;

import com.yy.educommons.ResultCommon;
import com.yy.eduvideoservice.service.VidService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ycr
 * @date 2020/7/8
 */
@RestController
@RequestMapping("/vidservice/vod")
@CrossOrigin
public class VidController {

    @Resource
    VidService vidService;


    @PostMapping("/upload")
    @ApiOperation("上传视频到阿里云")
    public ResultCommon uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        String videoId = vidService.uploadVideo(file);
        return ResultCommon.builder()
                .message("视频上传成功")
                .isOk(true)
                .code(20000)
                .data(videoId)
                .build();
    }

    @DeleteMapping("/{videoId}")
    @ApiOperation("删除阿里云上的视频")
    public ResultCommon deleteVideo(
            @PathVariable("videoId")
            @ApiParam(name = "videoId", value = "视频Id", required = true)
            String videoId){
        vidService.deleteVideo(videoId);
        return ResultCommon.success();
    }

    @DeleteMapping("/deleteMore")
    @ApiOperation("批量删除")
    public ResultCommon deleteVideos(@RequestParam("videoList")List<String> videos){
        vidService.deleteVideos(videos);
        return ResultCommon.success();
    }
}
