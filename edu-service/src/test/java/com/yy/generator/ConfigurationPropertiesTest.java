package com.yy.generator;

import com.yy.eduservice.util.ConstantPropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ycr
 * @date 2020/6/16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigurationPropertiesTest {

    @Autowired
    ConstantPropertiesUtil constantPropertiesUtil;

    @Test
    public void test(){
        System.out.println(ConstantPropertiesUtil.ACCESS_KEY_ID);
    }


}
