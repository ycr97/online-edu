package com.yy.eduservice.service;

import com.yy.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.eduservice.entity.vo.SubjectNestedVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-06-19
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 批量导入分类
     * @param file
     * @return
     */
    List<String> importSubject(MultipartFile file);


    List<SubjectNestedVO> nestedList();

    boolean saveOneLevel(EduSubject subject);


}
