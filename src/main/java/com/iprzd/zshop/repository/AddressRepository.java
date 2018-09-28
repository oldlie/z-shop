package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findTopByUserIdAndIsDefault(Long uid, int isDefault);
}
