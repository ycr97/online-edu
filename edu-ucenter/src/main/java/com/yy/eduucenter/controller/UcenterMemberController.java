package com.yy.eduucenter.controller;


import com.yy.educommons.ResultCommon;
import com.yy.eduucenter.entity.UcenterMember;
import com.yy.eduucenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author ycr
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/eduucenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Resource
    UcenterMemberService ucenterMemberService;

    @GetMapping("/getDayReg/{day}")
    public ResultCommon getRegisterNum(
            @ApiParam(name = "day", value = "某一天", required = true)
            @PathVariable("day") String day) {

        Integer result = ucenterMemberService.getDayRegNum(day);

        return ResultCommon.builder().data(result).code(20000).isOk(true).build();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultCommon loginUser(@RequestBody UcenterMember ucenterMember) {
        // ucenterMember中封装了手机号和密码
        // 调用service验证用户是否存在和密码是否正确
        String token = ucenterMemberService.login(ucenterMember);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResultCommon.success(map);
    }

}

