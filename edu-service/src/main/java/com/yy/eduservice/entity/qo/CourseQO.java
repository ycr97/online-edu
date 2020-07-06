package com.yy.eduservice.entity.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ycr
 * @date 2020/7/1
 */
@Data
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
public class CourseQO implements Serializable {

    private static final long serialVersionUID = 4486529163472280648L;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "讲师Id")
    private String teacherId;

    @ApiModelProperty(value = "一级分类")
    private String subjectParentId;

    @ApiModelProperty(value = "二级分类")
    private String subjectId;

}
