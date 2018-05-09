package com.iprzd.zshop.repository.commodity;

import com.iprzd.zshop.entity.commodity.Commodity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommodityRepository extends PagingAndSortingRepository<Commodity, Long> {
}
