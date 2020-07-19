package com.yy.eduucenter.controller;


import com.yy.educommons.ResultCommon;
import com.yy.eduucenter.service.UcenterMemberService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

}

