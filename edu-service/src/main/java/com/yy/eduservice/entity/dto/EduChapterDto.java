package com.yy.eduservice.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ycr
 * @date 2020/7/4
 */
@Data
public class EduChapterDto implements Serializable {

    private static final long serialVersionUID = -7540110742546391088L;

    private String id;

    private String title;

    private List<EduVideoDto> children = new ArrayList<>();
}
