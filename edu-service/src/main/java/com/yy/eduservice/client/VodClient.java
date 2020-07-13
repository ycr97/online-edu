package com.yy.eduservice.client;

import com.yy.educommons.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ycr
 * @date 2020/7/12
 */
@Component
@FeignClient(value = "edu-vod")
public interface VodClient {

    @DeleteMapping("/vidservice/vod/{videoId}")
    public ResultCommon deleteVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/vidservice/vod/deleteMore")
    public ResultCommon deleteVideos(@RequestParam("videoList") List<String> videos);
}
