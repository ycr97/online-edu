package com.yy.eduservice.mapper;

import com.yy.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author ycr
 * @since 2020-07-03
 */
public interface EduVideoMapper extends BaseMapper<EduVideo> {
    String getVideoIdByBarId(@Param("barId") String barId);

    List<String> getVideoIdByCourseId(@Param("courseId") String courseId);
}
