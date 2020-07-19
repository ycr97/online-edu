package com.yy.eduucenter;

import com.yy.eduucenter.mapper.UcenterMemberMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author ycr
 * @date 2020/7/13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    @Resource
    UcenterMemberMapper mapper;

    @Test
    public void test() {
        Integer dayRegNum = mapper.getDayRegNum("2019-01-01");
        System.out.println(dayRegNum);
    }
}
