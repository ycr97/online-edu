package com.yy.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.educms.entity.CrmBanner;
import com.yy.educommons.ResultCommon;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author ycr
 * @since 2020-07-19
 */
@RestController
@RequestMapping("/educms/bannerAdmin")
@CrossOrigin
public class  BannerAdminController {

    @GetMapping("pageBanner/{page}/{limit}")
    @ApiOperation("分页查询")
    public ResultCommon pageBanner(@ApiParam(name = "page", value = "第几页", required = true)
                                   @PathVariable long page,
                                   @ApiParam(name = "limit", value = "一页显示多少数据", required = true)
                                   @PathVariable
                                   long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        return null;
        
    }

}

