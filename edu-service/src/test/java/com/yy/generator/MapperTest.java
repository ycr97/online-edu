package com.yy.generator;

import com.yy.eduservice.entity.dto.CourseInfo;
import com.yy.eduservice.mapper.EduVideoMapper;
import com.yy.eduservice.service.EduCourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ycr
 * @date 2020/7/6
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {
    @Resource
    EduCourseService eduCourseService;

    @Resource
    EduVideoMapper mapper;

    @Test
    public void test() {
        CourseInfo courseAllInfo = eduCourseService.getBasicInfo("18");
        System.out.println(courseAllInfo);
    }

    @Test
    public void test1() {
        List<String> videoIdByCourseId = mapper.getVideoIdByCourseId("18");
        for (String s : videoIdByCourseId) {
            System.out.println(s);
        }
    }


}
