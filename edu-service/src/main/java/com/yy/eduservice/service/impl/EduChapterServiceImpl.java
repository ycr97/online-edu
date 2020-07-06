package com.yy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.eduservice.entity.EduChapter;
import com.yy.eduservice.entity.EduVideo;
import com.yy.eduservice.entity.dto.EduChapterDto;
import com.yy.eduservice.entity.dto.EduVideoDto;
import com.yy.eduservice.mapper.EduChapterMapper;
import com.yy.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.eduservice.service.EduVideoService;
import com.yy.exception.CustomException;
import com.yy.exception.CustomExceptionType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-07-03
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Resource
    private EduVideoService eduVideoService;

    @Override
    public boolean removeByCourseId(String id) {
        QueryWrapper<EduChapter> qw = new QueryWrapper<>();
        qw.eq("course_id", id);
        int delete = baseMapper.delete(qw);
        return delete > 0;
    }

    @Override
    public List<EduChapterDto> nestedList(String courseId) {

        List<EduChapterDto> list = new ArrayList<>();
        // 根据课程Id查询出章节信息
        QueryWrapper<EduChapter> qw = new QueryWrapper<>();
        qw.eq("course_id", courseId);
        qw.orderByAsc("sort", "id");
        List<EduChapter> eduChapters = baseMapper.selectList(qw);

        // 根据课程Id查询出小节信息
        List<EduVideo> eduVideos = eduVideoService.getVideoByCourseId(courseId);

        //将对应章节的小节封装到小节DTO中再放入在章节DTO中
        for (EduChapter chapter : eduChapters) {
            EduChapterDto chapterDto = new EduChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            List<EduVideoDto> children = chapterDto.getChildren();
            for (EduVideo eduVideo : eduVideos) {
                if (chapter.getId().equals(eduVideo.getChapterId())) {
                    EduVideoDto videoDto = new EduVideoDto();
                    BeanUtils.copyProperties(eduVideo, videoDto);
                    children.add(videoDto);
                }
            }
            list.add(chapterDto);
        }

        return list;
    }

    @Override
    public boolean removeChapterById(String id) {
        if (eduVideoService.getCountByChapterId(id)) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "该分章节下存在视频课程，请先删除视频课程");
        }
        int result = baseMapper.deleteById(id);
        return result > 0;
    }
}
