package com.yy.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.JsonObject;
import com.yy.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author ycr
 * @date 2020/7/23
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phone, Map<String, Object> param) {
        if (StringUtils.isEmpty(phone)) return false;
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI4GEJpPSkfiakyer4UPKC",
                "wr15SmIKfC3NAilCKI1LwQaRdOialf");
        DefaultAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request    = new CommonRequest();

        // 设置固定参数
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        // 设置发送相关参数
        request.putQueryParameter("PhoneNumbers", phone);// 手机号
        request.putQueryParameter("SignName", "我的优裕在线教育网站");// 申请阿里云 签名名称
        request.putQueryParameter("TemplateCode", "SMS_197610692");// 申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));// 验证码数据 转换json传递

        // 最终发送
        try {
            CommonResponse response  = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }


    }
}
