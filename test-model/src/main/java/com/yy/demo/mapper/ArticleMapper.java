package com.yy.demo.mapper;

import com.yy.demo.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author ycr
 * @since 2020-07-15
 */
public interface ArticleMapper extends BaseMapper<Article> {

    Article getArticleById(@Param("id") Integer id);

}
