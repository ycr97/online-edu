package com.yy.edustatistics.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ycr
 * @date 2020/7/16
 */
@Data
@ApiModel("统计图片查找条件对象")
public class ChartConditionDto implements Serializable {
    private static final long serialVersionUID = 7023098614170996857L;

    @ApiModelProperty(value = "查找类型")
    private String type;

    @ApiModelProperty(value = "查找开始时间")
    private String begin;

    @ApiModelProperty(value = "查找结束时间")
    private String end;
}
