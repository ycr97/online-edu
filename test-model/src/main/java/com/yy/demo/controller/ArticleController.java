package com.yy.demo.controller;


import com.yy.demo.entity.Article;
import com.yy.demo.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author ycr
 * @since 2020-07-15
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    ArticleService articleService;

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询")
    public Article getArticle(@PathVariable("id") Integer id) {
        Article article = articleService.getArticleById(id);
        return article;
    }

}

