package com.iprzd.zshop.repository.commodity;

import com.iprzd.zshop.entity.commodity.CommodityImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityImageRepository extends JpaRepository<CommodityImage, Long> {

    List<CommodityImage> findAllByCommodityIdOrderById(final Long id);
}
