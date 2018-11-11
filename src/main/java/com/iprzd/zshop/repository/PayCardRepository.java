package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.PayCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayCardRepository extends JpaRepository<PayCard, Long> {
}
