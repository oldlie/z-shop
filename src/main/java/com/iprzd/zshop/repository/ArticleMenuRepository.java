package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.article.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleMenuRepository extends JpaRepository<Menu, Long> {

    @Query("from com.iprzd.zshop.entity.article.Menu where id in ?1")
    List<Menu> findAllByIdIn(final List<Long> ids);

    void deleteAllByIdIn(final List<Long> ids);

    @Query("from com.iprzd.zshop.entity.article.Menu where parentId=?1")
    List<Menu> findByParentId(long id);
}
