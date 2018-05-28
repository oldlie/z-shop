package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.article.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleMenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByIdIn(final List<Long> ids);
}
