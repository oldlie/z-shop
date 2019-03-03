package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.PayCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayCardRepository extends JpaRepository<PayCard, Long> {

    Page<PayCard> findAllByIsValid(int isValid, Pageable pageable);

    PayCard findFirstByNumberAndVerifyCode(String number, String code);
}
