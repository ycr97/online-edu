package com.yy.eduucenter.mapper;

import com.yy.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author ycr
 * @since 2020-07-13
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer getDayRegNum(@Param("day") String day);
}
