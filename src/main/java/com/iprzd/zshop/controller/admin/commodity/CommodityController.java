package com.iprzd.zshop.controller.admin.commodity;

import com.iprzd.zshop.http.request.admin.commodity.CommodityRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.service.commodity.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/commodity")
public class CommodityController {

    private static Logger logger = LoggerFactory.getLogger(CommodityController.class);

    private CommodityService commodityService;

    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @PostMapping("/store")
    public BaseResponse store(CommodityRequest request) {
        return this.commodityService.store(request);
    }
}
