package com.yy.edustatistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.edustatistics.client.UCenterClient;
import com.yy.edustatistics.entity.StatisticsDaily;
import com.yy.edustatistics.entity.dto.ChartConditionDto;
import com.yy.edustatistics.mapper.StatisticsDailyMapper;
import com.yy.edustatistics.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-07-13
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Resource
    UCenterClient uCenterClient;

    @Override
    public void createStatisticsByDate(String day) {
        // 删除已存在的统计对象
        QueryWrapper<StatisticsDaily> qw = new QueryWrapper<>();
        qw.eq("date_calculated", day);
        baseMapper.delete(qw);

        // 获取统计数据
        Integer registerNum = (Integer)uCenterClient.getRegisterNum(day).getData();

        // 伪数据
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO

        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);

    }

    @Override
    public Map<String, Object> showChart(ChartConditionDto searchObj) {
        // 根据条件去statistics表中查询数据封装到Map对象中返回
        // 1.使用Mybatis-plus提供的方式
        QueryWrapper<StatisticsDaily> qw = new QueryWrapper<>();
        if (!StringUtils.isEmpty(searchObj.getBegin())) {
            qw.ge("date_calculated", searchObj.getBegin());
        }
        if (!StringUtils.isEmpty(searchObj.getEnd())) {
            qw.le("date_calculated", searchObj.getEnd());
        }

        qw.select(searchObj.getType(), "date_calculated");
        List<StatisticsDaily> dailies = baseMapper.selectList(qw);
        List<String> dates = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        for (StatisticsDaily daily : dailies) {
            dates.add(daily.getDateCalculated());
            switch (searchObj.getType()) {
                case "register_num":
                    nums.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    nums.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    nums.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    nums.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("dates", dates);
        map.put("nums", nums);
        return map;
    }
}
