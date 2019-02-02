package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findTopByUserIdOrderByIdDesc(Long uid);

    Address findTopByUserIdAndIsDefault(Long uid, int isDefault);

    List<Address> findAllByUserIdOrderByIdDesc(Long uid);
}
