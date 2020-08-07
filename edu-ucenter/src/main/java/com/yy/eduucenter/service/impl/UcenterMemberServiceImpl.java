package com.yy.eduucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.educommons.jwt.JwtUtils;
import com.yy.educommons.jwt.MD5;
import com.yy.eduucenter.entity.UcenterMember;
import com.yy.eduucenter.mapper.UcenterMemberMapper;
import com.yy.eduucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.exception.CustomException;
import com.yy.exception.CustomExceptionType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-07-13
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public Integer getDayRegNum(String day) {
        return baseMapper.getDayRegNum(day);
    }

    @Override
    public String login(UcenterMember ucenterMember) {
        // 获取登录手机号和密码
        String phone = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "登录失败");
        }
        // 判断手机号是否正确
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", ucenterMember.getMobile());
        UcenterMember member = baseMapper.selectOne(queryWrapper);

        // 判断查询对象是否为空
        if (member == null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "登录失败");
        }
        // 判断密码是否正确
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "登录失败");
        }
        if (member.getIsDisabled()) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "登录失败");
        }

        String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return jwtToken;
    }
}
