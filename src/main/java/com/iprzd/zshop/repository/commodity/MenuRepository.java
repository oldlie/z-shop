package com.iprzd.zshop.repository.commodity;

import com.iprzd.zshop.entity.commodity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByParentId(long id);
}
