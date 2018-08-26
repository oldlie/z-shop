package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    List<ShoppingCart> findAllByUidOrderByIdDesc(long uid);
}
