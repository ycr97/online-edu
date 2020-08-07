package com.yy.educms.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author ycr
 * @date 2020/7/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void testConnection() {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("redisTest", "TestConnection", 60, TimeUnit.SECONDS);
        System.out.println(operations.get("redisTest"));
    }
}
