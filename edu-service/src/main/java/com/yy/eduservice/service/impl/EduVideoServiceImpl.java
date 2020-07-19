package com.yy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.educommons.ResultCommon;
import com.yy.eduservice.client.VodClient;
import com.yy.eduservice.entity.EduVideo;
import com.yy.eduservice.mapper.EduVideoMapper;
import com.yy.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.exception.CustomException;
import com.yy.exception.CustomExceptionType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-07-03
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {


    @Resource
    VodClient vodClient;

    @Resource
    RestTemplate restTemplate;

    @Override
    public boolean deleteByCourseId(String courseId) {

        // 首先删除阿里云上的视频文件
        // 1.使用mybatis-plus提供的接口方式去查询出所有courseId为需要删除课程Id的videoSourceId
        QueryWrapper<EduVideo> videoQW = new QueryWrapper<>();
        videoQW.eq("course_id", courseId);
        videoQW.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(videoQW);
        List<String> videoIds = new ArrayList<>();

        for (EduVideo eduVideo : eduVideos) {
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoIds.add(videoSourceId);
            }
        }
        List<String> collect = videoIds.stream()
                .distinct()
                .collect(Collectors.toList());
        // 调用视频微服务删除云端视频
        if (videoIds.size() > 0) {
            // open feign 调用
            ResultCommon resultCommon = vodClient.deleteVideos(collect);

            // RestTemplate方式调用
            restTemplate.delete("http://EDU-VOD/vidservice/vod/deleteMore", collect, ResultCommon.class);
        }

        // 删除video表中course_id为需要删除课程Id的记录
        QueryWrapper<EduVideo> videoQW2 = new QueryWrapper<>();
        videoQW2.eq("course_id", courseId);
        int count = baseMapper.delete(videoQW2);

        return count > 0;
    }

    @Override
    public List<EduVideo> getVideoByCourseId(String courseId) {

        QueryWrapper<EduVideo> qw = new QueryWrapper<>();
        qw.eq("course_id", courseId);
        qw.orderByAsc("sort", "id");
        return baseMapper.selectList(qw);
    }

    @Override
    public boolean getCountByChapterId(String chapterId) {
        QueryWrapper<EduVideo> qw = new QueryWrapper<>();
        qw.eq("chapter_id", chapterId);
        Integer result = baseMapper.selectCount(qw);
        return result != null && result > 0;
    }

    @Override
    public boolean deleteBarById(String barId) {

        // 根据小节Id查询出视频Id
        String videoId = baseMapper.getVideoIdByBarId(barId);
        // 再调用Vod服务中的删除方法删除视频
        ResultCommon resultCommon = null;
        if (!StringUtils.isEmpty(videoId)) {
            resultCommon = vodClient.deleteVideo(videoId);
        }

        if (resultCommon != null && !resultCommon.getIsOk()) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "删除视频失败");
        }

        int i = baseMapper.deleteById(barId);


        return i > 0;
    }
}
