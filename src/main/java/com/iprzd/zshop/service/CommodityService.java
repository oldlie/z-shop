package com.iprzd.zshop.service;

import com.iprzd.zshop.entity.Commodity;
import com.iprzd.zshop.repository.CommodityRepository;
import org.springframework.stereotype.Service;

@Service
public class CommodityService {

    private CommodityRepository commodityRepository;

    public CommodityService(CommodityRepository commodityRepository){
        this.commodityRepository = commodityRepository;
    }

    public Commodity store(Commodity commodity) {
        return this.commodityRepository.save(commodity);
    }
}
