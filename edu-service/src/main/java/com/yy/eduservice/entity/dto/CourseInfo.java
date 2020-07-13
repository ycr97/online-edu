package com.yy.eduservice.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ycr
 * @date 2020/7/6
 */
@Data
@ToString
@ApiModel(value = "课程信息传输对象", description = "课程最终发布时视图显示的对象")
public class CourseInfo implements Serializable {

    private static final long serialVersionUID = 4190307312810045572L;

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程封面")
    private String cover;

    @ApiModelProperty("课程销售价格")
    private BigDecimal price;

    @ApiModelProperty("总课时")
    private Integer lessonNum;

    @ApiModelProperty("课程描述")
    private String description;

    @ApiModelProperty("讲师名称")
    private String teacherName;

    @ApiModelProperty("课程所属一级分类")
    private String levelOne;

    @ApiModelProperty("课程所属二级分类")
    private String levelTwo;

}
