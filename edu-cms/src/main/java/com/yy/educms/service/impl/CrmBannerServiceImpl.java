package com.yy.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.educms.entity.CrmBanner;
import com.yy.educms.mapper.CrmBannerMapper;
import com.yy.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-07-19
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {


    @Cacheable(value = "banner", key = "'selectIndex'")
    @Override
    public List<CrmBanner> selectIndexBanner() {


       return baseMapper.selectList(new QueryWrapper<CrmBanner>().orderByDesc("sort"));
    }

    @CacheEvict(value = "banner", key = "'selectIndex'")
    @Override
    public boolean updateBanner(CrmBanner crmBanner) {
        int i = baseMapper.updateById(crmBanner);

        return i > 0;

    }
}
