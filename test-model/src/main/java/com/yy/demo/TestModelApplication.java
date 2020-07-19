package com.yy.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ycr
 * @date 2020/7/15
 */
@SpringBootApplication
@MapperScan("com.yy.demo.mapper")
public class TestModelApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestModelApplication.class, args);
    }
}
