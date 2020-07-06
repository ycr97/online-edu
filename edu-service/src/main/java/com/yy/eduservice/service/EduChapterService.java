package com.yy.eduservice.service;

import com.yy.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.eduservice.entity.dto.EduChapterDto;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-07-03
 */
public interface EduChapterService extends IService<EduChapter> {

    boolean removeByCourseId(String id);

    List<EduChapterDto> nestedList(String courseId);

    boolean removeChapterById(String id);
}
