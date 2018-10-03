package com.iprzd.zshop.controller.admin.commodity;

import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.request.admin.commodity.CommodityRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.CommodityImageListResponse;
import com.iprzd.zshop.http.response.CommodityImageResponse;
import com.iprzd.zshop.http.response.admin.commodity.CommodityListResponseBase;
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
    public CommodityListResponseBase list(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam String orderBy,
                                          @RequestParam int order) {
        return this.commodityService.findAll(new ListRequest(page, size, orderBy, order));
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody IdRequest request) {
        return this.commodityService.delete(request);
    }

    @PostMapping("/home")
    public BaseResponse add2Home(@RequestBody IdRequest request) {
        return this.commodityService.add2Home(request.getId());
    }

    @PostMapping("/images")
    public CommodityImageListResponse listCommodityImage(@RequestBody IdRequest request) {
        return this.commodityService.listCommodityImage(request.getId());
    }

    @PostMapping("/image")
    public CommodityImageResponse findCommodityImage(@RequestBody IdRequest request) {
        return this.commodityService.findCommodityImage(request.getId());
    }
}
