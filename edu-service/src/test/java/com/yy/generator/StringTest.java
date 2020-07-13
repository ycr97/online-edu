package com.yy.generator;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycr
 * @date 2020/7/12
 */
public class StringTest {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        String join = StringUtils.join(list.toArray(), ",");
        System.out.println(join);
    }
}
