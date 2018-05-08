package com.iprzd.zshop.repository;

import com.iprzd.zshop.entity.Commodity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommodityRepository extends PagingAndSortingRepository<Commodity, Long> {
}
