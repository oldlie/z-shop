package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.SettlementCartMap;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementCartMapRepository extends JpaRepository<SettlementCartMap, Long> {

    List<SettlementCartMap> findAllBySettlementId(Long settlementId);
}