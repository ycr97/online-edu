package com.yy.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author ycr
 * @since 2020-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Article对象", description="文章")
public class Article implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String author;

    private String title;

    private String content;

    private Date createTime;


}
