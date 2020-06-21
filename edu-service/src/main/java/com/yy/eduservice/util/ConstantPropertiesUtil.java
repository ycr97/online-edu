package com.yy.eduservice.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ycr
 * @date 2020/6/16
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class ConstantPropertiesUtil implements InitializingBean {

    private String endPoint;

    private String  accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    public static String END_POINT;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    public static String BUCKET_NAME;

    public static String FILE_HOST ;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endPoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
    }
}
