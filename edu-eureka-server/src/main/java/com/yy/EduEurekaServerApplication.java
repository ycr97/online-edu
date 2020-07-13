package com.yy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ycr
 * @date 2020/7/12
 */
@SpringBootApplication
@EnableEurekaServer
public class EduEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduEurekaServerApplication.class, args);
    }
}
