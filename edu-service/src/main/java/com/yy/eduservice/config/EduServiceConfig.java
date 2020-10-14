package com.yy.eduservice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ycr
 * @date 2020/5/31
 */
@Configuration
public class EduServiceConfig {

    @Bean
    PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
