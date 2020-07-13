package com.yy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ycr
 * @date 2020/5/30
 */
@SpringBootApplication
@MapperScan("com.yy.eduservice.mapper")
@ComponentScan(basePackages = {"com.yy.eduservice", "com.yy.educommons"})
@EnableEurekaClient
@EnableFeignClients
public class EduServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduServiceApplication.class, args);
    }
}
