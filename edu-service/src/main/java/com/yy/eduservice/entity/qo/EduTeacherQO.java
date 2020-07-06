package com.yy.eduservice.entity.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ycr
 * @date 2020/6/2
 */
@Data
public class EduTeacherQO {

    @ApiModelProperty(value = "讲师名称")
    private String name;

    @ApiModelProperty(value = "讲师头衔")
    private String level;

    @ApiModelProperty(value = "添加时间起始")
    private String begin;

    @ApiModelProperty(value = "添加时间结束")
    private String end;

}
