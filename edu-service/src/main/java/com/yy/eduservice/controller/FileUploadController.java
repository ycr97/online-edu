package com.yy.eduservice.controller;

import com.yy.educommons.ResultCommon;
import com.yy.eduservice.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ycr
 * @date 2020/6/15
 */
@Api(description="阿里云文件管理")
@RestController
@RequestMapping("/eduservice/upload")
@CrossOrigin
public class FileUploadController {

    @Resource
    FileUploadService fileUploadService;

    @PostMapping("upload")
    public ResultCommon uploadImage(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file")MultipartFile file){
        String uploadFile = fileUploadService.UploadFile(file);
        Map<String, String> map = new HashMap<>();
        map.put("url", uploadFile);
        return ResultCommon.success(map);
    }
}
