package com.yy.eduvideoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ycr
 * @date 2020/7/8
 *
 * */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class EduVideoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduVideoServiceApplication.class, args);
    }
}
