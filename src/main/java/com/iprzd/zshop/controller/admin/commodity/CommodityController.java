package com.iprzd.zshop.controller.admin.commodity;

import com.iprzd.zshop.controller.response.BaseResponse;
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
    public BaseResponse store(Commodity commodity) {
        BaseResponse response = new BaseResponse();
        commodity = this.commodityService.store(commodity);
        if (commodity.getId() > 0) {
            response.setStatus(0);
        } else {
            response.setStatus(2);
            response.setMessage("保存商品操作未成功。");
            logger.debug("保存商品操作未成功");
            logger.debug(commodity.toString());
        }
        return response;
    }
}
