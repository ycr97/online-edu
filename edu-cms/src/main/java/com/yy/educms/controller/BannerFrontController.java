package com.yy.educms.controller;

import com.yy.educms.entity.CrmBanner;
import com.yy.educms.service.CrmBannerService;
import com.yy.educommons.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ycr
 * @date 2020/7/19
 */
@RestController
@CrossOrigin
@RequestMapping("/educms/bannerFront")
public class BannerFrontController {

    @Resource
    private CrmBannerService crmBannerService;

    @GetMapping
    @ApiOperation("前台获取所有banner")
    public ResultCommon getAllBanner() {
        List<CrmBanner> list = crmBannerService.selectIndexBanner();
        return ResultCommon.success(list);
    }

}
