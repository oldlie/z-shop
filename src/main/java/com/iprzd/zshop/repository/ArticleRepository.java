package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.article.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    @Query(value = "select * from t_article a, t_home_article h " +
            "where a.id = h.article_id " +
            "order by h.id desc",
            nativeQuery = true)
    List<Article> findAllHomeArticles();
}
