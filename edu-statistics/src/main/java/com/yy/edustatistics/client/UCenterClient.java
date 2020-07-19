package com.yy.edustatistics.client;

import com.yy.educommons.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "edu-ucenter")
public interface UCenterClient {

    @GetMapping("/eduucenter/member/getDayReg/{day}")
    public ResultCommon getRegisterNum(@PathVariable("day") String day);
}
