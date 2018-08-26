package com.iprzd.zshop.controller.front;

import com.iprzd.zshop.entity.ShoppingCart;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ShoppingCartRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.ListResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/front/shopping")
public class FrontShoppingController {

    private ShoppingService shoppingService;

    @Autowired
    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping("/cart")
    public SimpleResponse<Long> storeShoppingCart(@RequestBody ShoppingCartRequest request) {
        return this.shoppingService.storeShoppingCart(request);
    }

    @DeleteMapping("/cart")
    public BaseResponse deleteShoppingCart(@RequestParam Long id) {
        return this.shoppingService.deleteShoppingCart(id);
    }

    @GetMapping("/cart")
    public ListResponse<ShoppingCart> findAllShoppingCart(@RequestParam Long uid) {
        return this.shoppingService.findShoppingCartByUid(uid);
    }
}
