package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.search.service
 * @className:ArticleService
 * @author:larry
 * @date:2019/12/27 14:30
 * @description:
 */
@Service
@Transactional  //可加可不加 因为不是与数据库交互
public class ArticleService {
    @Autowired
    private ArticleSearchDao articleSearchDao;

    @Autowired
    private IdWorker idWorker;

    public void save(Article article) {
        article.setId(idWorker.nextId() + "");
        articleSearchDao.save(article);
    }


    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> articlePage = articleSearchDao.findByTitleOrContentLike(key, key, pageable);
        return articlePage;
    }
}
