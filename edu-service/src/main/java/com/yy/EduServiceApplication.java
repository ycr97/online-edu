package com.yy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ycr
 * @date 2020/5/30
 */
@SpringBootApplication
@MapperScan("com.yy.eduservice.mapper")
@ComponentScan(basePackages = {"com.yy.eduservice", "com.yy.educommons"})
public class EduServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduServiceApplication.class, args);
    }
}
