package com.yy.eduservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {


    String UploadFile(MultipartFile file);
}
