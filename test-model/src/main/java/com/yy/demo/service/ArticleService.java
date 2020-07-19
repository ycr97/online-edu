package com.yy.demo.service;

import com.yy.demo.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author ycr
 * @since 2020-07-15
 */
public interface ArticleService extends IService<Article> {

    Article getArticleById(Integer id);
}
