package com.yy.eduservice.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycr
 * @date 2020/6/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectNestedVO {

    private String id;

    private String title;

    private List<SubjectVO> children = new ArrayList<>();
}
