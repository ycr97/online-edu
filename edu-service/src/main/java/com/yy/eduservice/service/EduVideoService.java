package com.yy.eduservice.service;

import com.yy.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-07-03
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean deleteByCourseId(String id);

    List<EduVideo> getVideoByCourseId(String courseId);

    boolean getCountByChapterId(String chapterId);
}
