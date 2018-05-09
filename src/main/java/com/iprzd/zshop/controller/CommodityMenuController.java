package com.iprzd.zshop.controller;

import com.iprzd.zshop.controller.response.BaseResponse;
import com.iprzd.zshop.controller.response.CommodityMenuListResponse;
import com.iprzd.zshop.entity.commodity.Menu;
import com.iprzd.zshop.service.CommodityMenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/commodity/menu")
public class CommodityMenuController {

    private CommodityMenuService commodityMenuService;

    public CommodityMenuController(CommodityMenuService commodityMenuService) {
        this.commodityMenuService = commodityMenuService;
    }

    @PostMapping("/store")
    public BaseResponse store(@RequestParam("title") String title,
                              @RequestParam("parentId") long parentId,
                              @RequestParam("comment") String comment) {
        Menu menu = new Menu();
        menu.setTitle(title);
        menu.setParentId(parentId);
        menu.setComment(comment);
        return this.commodityMenuService.store(menu);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestParam("id") long id,
                               @RequestParam("title") String title,
                               @RequestParam("parentId") long parentId,
                               @RequestParam("comment") String comment) {
        return this.commodityMenuService.update(id, title, parentId, comment);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestParam("id") long id) {
        return this.commodityMenuService.delete(id);
    }

    @GetMapping("/children/{id}")
    public CommodityMenuListResponse findByParentId(@PathVariable("id") long id) {
        return this.commodityMenuService.findByParentId(id);
    }
}
