package com.yy.eduvideoservice;

import com.yy.eduvideoservice.util.VodConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

/**
 * @author ycr
 * @date 2020/7/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VidTest {

    @Resource
    VodConstant vodConstant;

    @Test
    public void test(){
        System.out.println(VodConstant.ACCESS_KEY_ID);
    }

    @Test
    public void test1() {
        System.out.println(vodConstant);
    }
}
