package com.yy.edustatistics.controller;


import com.yy.educommons.ResultCommon;
import com.yy.edustatistics.entity.dto.ChartConditionDto;
import com.yy.edustatistics.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author ycr
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/edustatistics/daily")
@CrossOrigin
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService dailyService;

    @PostMapping("/{day}")
    @ApiOperation("生成某一天统计数据")
    public ResultCommon createStatisticsByDate(
            @ApiParam(name = "day", value = "某一天", required = true)
            @PathVariable("day") String day) {

        dailyService.createStatisticsByDate(day);
        return ResultCommon.success();
    }

    @PostMapping("/showChart")
    @ApiOperation("根据条件显示图表")
    public ResultCommon showChart(
            @ApiParam(name = "searchObj", value = "查找条件对象", required = true)
            @RequestBody ChartConditionDto chartConditionDto) {
        Map<String, Object> result = dailyService.showChart(chartConditionDto);
        return ResultCommon
                .builder()
                .isOk(true)
                .code(20000)
                .data(result)
                .build();
    }
}

