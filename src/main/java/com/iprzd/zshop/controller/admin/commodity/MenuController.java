package com.iprzd.zshop.controller.admin.commodity;

import com.iprzd.zshop.controller.admin.request.IdRequest;
import com.iprzd.zshop.controller.response.BaseResponse;
import com.iprzd.zshop.controller.response.CommodityMenuListResponse;
import com.iprzd.zshop.entity.commodity.Menu;
import com.iprzd.zshop.service.CommodityMenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/commodity/menu")
public class MenuController {

    private CommodityMenuService commodityMenuService;

    public MenuController(CommodityMenuService commodityMenuService) {
        this.commodityMenuService = commodityMenuService;
    }

    @PostMapping("/store")
    public BaseResponse store(@RequestBody Menu menu) {
        return this.commodityMenuService.store(menu);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody IdRequest request) {
        return this.commodityMenuService.delete(request.getId());
    }

    @GetMapping("/children")
    public CommodityMenuListResponse findByParentId(@RequestParam("id") long id) {
        return this.commodityMenuService.findByParentId(id);
    }
}
