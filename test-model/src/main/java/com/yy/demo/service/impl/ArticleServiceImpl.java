package com.yy.demo.service.impl;

import com.yy.demo.entity.Article;
import com.yy.demo.mapper.ArticleMapper;
import com.yy.demo.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.core.instrument.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author ycr
 * @since 2020-07-15
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public Article getArticleById(Integer id) {

        String key = "article_" + id;

        ValueOperations<String, Article> valueOperations = redisTemplate.opsForValue();

        Boolean hasKey = redisTemplate.hasKey(key);


        if (hasKey) {
            long begin = System.currentTimeMillis();
            Article article = valueOperations.get(key);
            long end = System.currentTimeMillis();
            log.info("查询Redis执行了: "+ (end - begin) +"*******************");
            return article;
        }else {

            long begin = System.currentTimeMillis();
            Article article = baseMapper.getArticleById(id);
            long end = System.currentTimeMillis();
            log.info("查询数据库执行了: "+ (end - begin) +"*******************");
            valueOperations.set(key, article, 100, TimeUnit.SECONDS);
            return article;
        }


    }
}
