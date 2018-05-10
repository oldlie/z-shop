package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.article.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
}
