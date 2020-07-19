package com.yy.eduservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author ycr
 * @date 2020/7/15
 */
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(30000); //设置超时
        factory.setReadTimeout(30000);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);

        return restTemplate;
    }
}
