package com.yy.edustatistics.service;

import com.yy.edustatistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.edustatistics.entity.dto.ChartConditionDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-07-13
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDate(String day);

    Map<String, Object> showChart(ChartConditionDto searchObj);
}
