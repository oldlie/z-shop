package com.iprzd.zshop.repository.commodity;

import com.iprzd.zshop.entity.commodity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("from com.iprzd.zshop.entity.commodity.Menu where parentId=?1")
    List<Menu> findByParentId(long id);
}
