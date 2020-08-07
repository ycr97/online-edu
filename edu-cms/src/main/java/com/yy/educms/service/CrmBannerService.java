package com.yy.educms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.educms.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-07-19
 */
public interface CrmBannerService extends IService<CrmBanner> {


    List<CrmBanner> selectIndexBanner();

    boolean updateBanner(CrmBanner crmBanner);
}
