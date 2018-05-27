package com.iprzd.zshop.controller.admin.commodity;

import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.request.admin.commodity.CommodityRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.admin.commodity.CommodityListResponse;
import com.iprzd.zshop.service.commodity.CommodityService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/commodity")
public class CommodityController {

    private CommodityService commodityService;

    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @PostMapping("/store")
    public BaseResponse store(@RequestBody CommodityRequest request) {
        return this.commodityService.store(request);
    }

    @GetMapping("/list")
    public CommodityListResponse list(@RequestParam int page,
                                      @RequestParam int size,
                                      @RequestParam String orderBy,
                                      @RequestParam int order) {
        return this.commodityService.findAll(new ListRequest(page, size, orderBy, order));
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody IdRequest request) {
        return this.commodityService.delete(request);
    }
}
