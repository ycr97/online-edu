package com.yy.eduservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.yy.eduservice.service.FileUploadService;
import com.yy.eduservice.util.ConstantPropertiesUtil;
import com.yy.exception.CustomException;
import com.yy.exception.CustomExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author ycr
 * @date 2020/6/15
 */
@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {


    @Override
    public String UploadFile(MultipartFile file) {

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String uploadUrl = "https://";

        String filename = file.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();

        filename = uuid + filename;

        String filePath = new DateTime().toString("yyyy/MM/dd");

        filename = filePath + filename;

        try {
            InputStream inputStream = file.getInputStream();

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filename, inputStream);
            // 上传
            ossClient.putObject(putObjectRequest);

            // 关闭OSSClient。
            ossClient.shutdown();
            uploadUrl =  uploadUrl + bucketName + "." + endpoint + "/" + filename;
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, e.getMessage());
        }
        return uploadUrl;
    }
}
