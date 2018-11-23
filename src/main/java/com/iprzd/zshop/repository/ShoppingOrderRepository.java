package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.ShoppingOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Long> {

    ShoppingOrder findFirstByUserId(Long uid);

    Page<ShoppingOrder> findAllByUserId(Pageable pageable);
}
