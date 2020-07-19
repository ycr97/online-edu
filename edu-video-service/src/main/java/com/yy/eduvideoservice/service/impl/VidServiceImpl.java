package com.yy.eduvideoservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.yy.eduvideoservice.service.VidService;
import com.yy.eduvideoservice.util.AliyunVodSDKUtils;
import com.yy.eduvideoservice.util.VodConstant;
import com.yy.exception.CustomException;
import com.yy.exception.CustomExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ycr
 * @date 2020/7/8
 */
@Service
@Slf4j
public class VidServiceImpl implements VidService {

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            // 获取文件输入流
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            assert filename != null;
            // 根据文件名确定上传名称
            String title = filename.substring(0, filename.lastIndexOf("."));
            // 上传请求对象
            UploadStreamRequest request = new UploadStreamRequest(VodConstant.ACCESS_KEY_ID
                    , VodConstant.ACCESS_KEY_SECRET, title, filename, inputStream);

            UploadVideoImpl uploadVideo = new UploadVideoImpl();
            UploadStreamResponse response = uploadVideo.uploadStream(request);
            // 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误: " + "code" + response.getCode() + "message: " + response.getMessage();
                log.warn(errorMessage);
                if (StringUtils.isEmpty(videoId)) {
                    throw new CustomException(CustomExceptionType.SYSTEM_ERROR, errorMessage);
                }
            }
            return videoId;
        } catch (IOException e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "系统上传出错");
        }
    }

    @Override
    public void deleteVideo(String videoId) {
        System.out.println(videoId);
        System.out.println("11111");
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(VodConstant.ACCESS_KEY_ID, VodConstant.ACCESS_KEY_SECRET);
            // 创建删除视频请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //设置删除视频Id
            request.setVideoIds(videoId);
            // 调用方法删除
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());

        } catch (ClientException e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "视频删除失败");
        }
    }

    @Override
    public void deleteVideos(List videos) {
        String videoIds = StringUtils.join(videos.toArray(), ",");
        System.out.println(videoIds);
        deleteVideo(videoIds);
    }


}
