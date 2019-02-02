package com.iprzd.zshop.repository.home;

import com.iprzd.zshop.entity.home.HomeCommodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeCommodityRepository extends JpaRepository<HomeCommodity, Long> {

    HomeCommodity findFirstByCommodityId(final Long id);

    void deleteByCommodityId(final Long id);
}
