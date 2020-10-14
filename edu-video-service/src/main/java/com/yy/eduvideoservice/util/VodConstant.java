package com.yy.eduvideoservice.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ycr
 * @date 2020/7/8
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "aliyun.vod")
public class VodConstant implements InitializingBean {

    private String accessKeyId;

    private String accessKeySecret;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
    }
}
