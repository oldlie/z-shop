package com.iprzd.zshop.controller.front;

import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.response.CommodityInfoResponse;
import com.iprzd.zshop.http.response.admin.commodity.CommodityListResponse;
import com.iprzd.zshop.service.commodity.CommodityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodity")
public class FrontCommodityController {

    private CommodityService commodityService;

    public FrontCommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @GetMapping("/detail")
    public CommodityInfoResponse detail(@RequestParam long id) {
        return this.commodityService.findOneById(id);
    }

    @GetMapping("/list")
    public CommodityListResponse list(@RequestParam int page, @RequestParam int size, @RequestParam String orderBy,
            @RequestParam int order) {
        return this.commodityService.findAll(new ListRequest(page, size, orderBy, order));
    }

}
