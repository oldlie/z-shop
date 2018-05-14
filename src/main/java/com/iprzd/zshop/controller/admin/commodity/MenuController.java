package com.iprzd.zshop.controller.admin.commodity;

import com.iprzd.zshop.controller.admin.request.IdRequest;
import com.iprzd.zshop.controller.response.BaseResponse;
import com.iprzd.zshop.controller.response.CommodityMenuListResponse;
import com.iprzd.zshop.entity.commodity.Menu;
import com.iprzd.zshop.service.commodity.MenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/commodity/menu")
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/store")
    public BaseResponse store(@RequestBody Menu menu) {
        return this.menuService.store(menu);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody IdRequest request) {
        return this.menuService.delete(request.getId());
    }

    @GetMapping("/children")
    public CommodityMenuListResponse findByParentId(@RequestParam("id") long id) {
        return this.menuService.findByParentId(id);
    }
}
