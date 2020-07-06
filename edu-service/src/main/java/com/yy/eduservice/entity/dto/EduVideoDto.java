package com.yy.eduservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ycr
 * @date 2020/7/4
 */
@Data
public class EduVideoDto implements Serializable {

    private static final long serialVersionUID = 5790236229904036102L;

    private String id;

    private String title;
}
