package com.yy.msmservice.controller;

import com.yy.educommons.ResultCommon;
import com.yy.educommons.jwt.RandomUtil;
import com.yy.msmservice.service.MsmService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ycr
 * @date 2020/7/23
 */
@RestController
@RequestMapping("/msmservice/msm")
@CrossOrigin
public class MsmController {

    @Resource
    private MsmService msmService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation("发送短信")
    @GetMapping("/send/{phone}")
    public ResultCommon sendMsm(@PathVariable String phone) {

        String code = redisTemplate.opsForValue().get(phone);

        if (!StringUtils.isEmpty(code)) {
            return ResultCommon.success();
        }

        // 生成随机值传递给阿里云发送
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isOk = msmService.send(phone, param);
        if (isOk) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return ResultCommon.success();
        } else {
            return ResultCommon.builder()
                    .isOk(false)
                    .message("短信发送失败")
                    .code(50000)
                    .build();
        }
    }

}
