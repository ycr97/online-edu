package com.yy.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.educms.entity.CrmBanner;
import com.yy.educms.service.CrmBannerService;
import com.yy.educommons.ResultCommon;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private CrmBannerService crmBannerService;

    @GetMapping("pageBanner/{page}/{limit}")
    @ApiOperation("分页查询banner")
    public ResultCommon pageBanner(@ApiParam(name = "page", value = "第几页", required = true)
                                   @PathVariable long page,
                                   @ApiParam(name = "limit", value = "一页显示多少数据", required = true)
                                   @PathVariable
                                   long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);

        crmBannerService.page(pageBanner, null);
        List<CrmBanner> records = pageBanner.getRecords();
        long total = pageBanner.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("total", total);
        return ResultCommon.success(map);
        
    }

    @PostMapping
    @ApiOperation("添加banner")
    public ResultCommon addBanner(@ApiParam(name = "crmBanner", value = "banner对象", required = true)
                                  @RequestBody
                                  CrmBanner crmBanner){
        crmBannerService.save(crmBanner);

        return null;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除banner")
    public ResultCommon deleteBanner(@ApiParam(name = "id", value = "bannerId", required = true)
                                     @PathVariable("id") String id) {
        crmBannerService.removeById(id);
        return ResultCommon.success();
    }

    @PutMapping
    @ApiOperation("修改banner")
    public ResultCommon updateBannerById(@ApiParam(name = "banner", value = "banner", required = true)
                                         @RequestBody CrmBanner crmBanner) {

        crmBannerService.updateBanner(crmBanner);

        return ResultCommon.success();
    }

}

