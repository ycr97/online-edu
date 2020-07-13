package com.yy.eduucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ycr
 * @date 2020/7/13
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yy.eduucenter.mapper")
public class EduUCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduUCenterApplication.class, args);
    }
}
