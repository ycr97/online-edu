package com.yy.eduucenter.controller;


import com.yy.educommons.ResultCommon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class UcenterMemberController {

    @GetMapping("/{day}")
    public ResultCommon getRegisterNum() {


        return null;
    }

}

