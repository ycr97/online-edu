package com.yy.eduucenter.service;

import com.yy.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-07-13
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    Integer getDayRegNum(String day);
}
