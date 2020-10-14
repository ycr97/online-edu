package com.yy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.eduservice.entity.EduSubject;
import com.yy.eduservice.entity.vo.SubjectNestedVO;
import com.yy.eduservice.entity.vo.SubjectVO;
import com.yy.eduservice.mapper.EduSubjectMapper;
import com.yy.eduservice.service.EduSubjectService;
import com.yy.exception.CustomException;
import com.yy.exception.CustomExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-06-19
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public List<String> importSubject(MultipartFile file) {

        List<String> list = new ArrayList<>();

        // 根据file获取workbook对象
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(file.getInputStream());

            // 根据workbook获取sheet对象
            Sheet sheet = workbook.getSheetAt(0);
            // 根据sheet获取row对象
            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum <= 1) {
                list.add("表内数据不能为空");
                return list;
            }
            String parentId;
            for (int i = 1; i <= lastRowNum; i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    list.add("第" + i + "行数据为空");
                    continue;
                }
                Cell cellOne = row.getCell(0);

                // 添加一级分类
                //  excel表中数据有很多重复的一级分类
                //在添加一级分类的之前需要判断,判断要添加的一级分类在数据中是否存在
                if (cellOne != null) {
                    String cellStr1 = cellOne.getStringCellValue();
                    EduSubject eduSubject = subjectIsExist(cellStr1, "0");
                    if (eduSubject == null) {
                        EduSubject subject = new EduSubject();
                        subject.setTitle(cellStr1);
                        subject.setParentId("0");
                        subject.setSort(0);
                        baseMapper.insert(subject);
                        parentId = subject.getId();
                    } else {
                        parentId = eduSubject.getId();
                    }
                } else {
                    // TODO
                    list.add("第" + i + "行一级分类为空");
                    continue;
                }

                Cell cellTwo = row.getCell(1);
                if (cellTwo != null) {
                    String cellStr2 = cellTwo.getStringCellValue();
                    EduSubject subject = subjectIsExist(cellStr2, parentId);
                    if (subject == null) {
                        EduSubject subject1 = new EduSubject();
                        subject1.setSort(0);
                        subject1.setParentId(parentId);
                        subject1.setTitle(cellStr2);
                        baseMapper.insert(subject1);
                    }
                } else {
                    // TODO
                    list.add("第" + i + "行二级分类为空");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "导入失败");
        }
        return list;
    }

    /**
     * 嵌套数据列表
     *
     * @return list
     */
    @Override
    public List<SubjectNestedVO> nestedList() {

        // 拿到一级分类
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id", "0");
        wrapper1.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(wrapper1);

        // 拿到二级分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id", "0");
        wrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(wrapper2);

        List<SubjectNestedVO> snVo = new ArrayList<>();

        int oneNum = subjects.size();
        for (EduSubject subject : subjects) {
            SubjectNestedVO subjectNestedVO = new SubjectNestedVO();
            BeanUtils.copyProperties(subject, subjectNestedVO);

            for (EduSubject s : subSubjects) {
                if (s.getParentId().equals(subject.getId())) {

                    SubjectVO subjectVO = new SubjectVO();
                    BeanUtils.copyProperties(s, subjectVO);
                    subjectNestedVO.getChildren().add(subjectVO);
                }
            }
            snVo.add(subjectNestedVO);
        }

        return snVo;
    }

    @Override
    public boolean removeOneLevel(String parent_id) {
        QueryWrapper<EduSubject> qw = new QueryWrapper<>();
        qw.eq("parent_id", parent_id);

        Integer count = baseMapper.selectCount(qw);

        if (count > 0) {
            return false;
        } else {
            int result = baseMapper.deleteById(parent_id);
            return result > 0;
        }

    }

    @Override
    public boolean saveOneLevel(EduSubject subject) {
        EduSubject eduSubject = this.subjectIsExist(subject.getTitle(), "0");
        boolean success = false;
        if (eduSubject == null) {
            subject.setParentId("0");
            subject.setSort(0);
            int i = baseMapper.insert(subject);
            if (i > 0) {
                success = true;
            }
        } else {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "类别已存在");
        }
        return success;
    }

    @Override
    public boolean saveTwoLevel(EduSubject subject) {
        EduSubject eduSubject = this.subjectIsExist(subject.getTitle(), subject.getParentId());
        if (eduSubject == null) {
            subject.setSort(0);
            return this.save(subject);
        } else {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "类别已存在");
        }

    }

    /**
     * @param title
     * @return
     */
    private EduSubject subjectIsExist(String title, String parent) {

        QueryWrapper<EduSubject> qw = new QueryWrapper<>();
        qw.eq("title", title);
        qw.eq("parent_id", parent);
        return baseMapper.selectOne(qw);
    }
}
