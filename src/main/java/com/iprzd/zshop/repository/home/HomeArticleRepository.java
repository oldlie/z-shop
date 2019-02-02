package com.iprzd.zshop.repository.home;

import com.iprzd.zshop.entity.home.HomeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HomeArticleRepository extends JpaRepository<HomeArticle, Long> {

    HomeArticle findFirstByArticleId(final Long id);
}
