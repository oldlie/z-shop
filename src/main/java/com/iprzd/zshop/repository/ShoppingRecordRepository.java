package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.ShoppingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingRecordRepository extends JpaRepository<ShoppingRecord, Long> {
}
