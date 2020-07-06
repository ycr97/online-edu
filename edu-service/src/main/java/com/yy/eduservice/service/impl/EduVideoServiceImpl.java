package com.yy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.eduservice.entity.EduVideo;
import com.yy.eduservice.mapper.EduVideoMapper;
import com.yy.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public boolean deleteByCourseId(String id) {
        QueryWrapper<EduVideo> qw = new QueryWrapper<>();
        int delete = baseMapper.delete(qw);
        return delete > 0;
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
}
