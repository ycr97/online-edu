package com.yy.eduvideoservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VidService {

    String uploadVideo(MultipartFile file);


    void deleteVideo(String videoId);

    void deleteVideos(List videos);
}
