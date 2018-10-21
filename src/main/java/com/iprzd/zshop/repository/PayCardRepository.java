package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.PayCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayCardRepository extends JpaRepository<PayCardEntity, Long> {
}
