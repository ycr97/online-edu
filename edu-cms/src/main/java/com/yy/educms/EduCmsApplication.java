package com.yy.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ycr
 * @date 2020/7/19
 */
@SpringBootApplication
@MapperScan("com.yy.educms.mapper")
public class EduCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduCmsApplication.class, args);
    }
}
