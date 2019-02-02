package com.iprzd.zshop.repository.commodity;

import com.iprzd.zshop.entity.commodity.Commodity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommodityRepository extends PagingAndSortingRepository<Commodity, Long> {

    @Query(value = "SELECT * FROM t_commodity a, t_home_commodity b "
            + " where a.id = b.commodity_id order by b.sequence asc",
            nativeQuery = true)
    List<Commodity> findAllHomePageCommodities();
}
