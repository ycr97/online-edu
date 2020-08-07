package com.yy.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ycr
 * @date 2020/7/23
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MsmServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmServiceApplication.class, args);
    }
}
