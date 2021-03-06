package com.iprzd.zshop.controller.front;

import com.iprzd.zshop.entity.Address;
import com.iprzd.zshop.entity.ShoppingCartItem;
import com.iprzd.zshop.entity.ShoppingOrder;
import com.iprzd.zshop.http.request.AddressRequest;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.RechargeRequest;
import com.iprzd.zshop.http.request.ShoppingCartRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.ListResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.model.SettlementModel;
import com.iprzd.zshop.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RolesAllowed({"USER", "ADMIN"})
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
    public ListResponse<ShoppingCartItem> findAllShoppingCart(@RequestParam Long uid) {
        return this.shoppingService.findShoppingCartByUid(uid);
    }

    @GetMapping("/order")
    public SimpleResponse<ShoppingOrder> findFirstShoppingOrderByUid(@RequestParam Long uid) {
        return this.shoppingService.findShoppingOrderByUid(uid);
    }

    @PostMapping("/address")
    public SimpleResponse<Long> storeAddress(@RequestBody AddressRequest request) {
        return this.shoppingService.storeAddress(request.toAddress());
    }

    @PutMapping("/address")
    public BaseResponse putAddress(@RequestBody AddressRequest request) {
        return this.shoppingService.storeAddress(request.toAddress());
    }

    @GetMapping("/address")
    public SimpleResponse<Address> findTopByAddress(@RequestParam Long uid) {
        return this.shoppingService.findDefaultAddressByUid(uid);
    }

    @GetMapping("/addresses")
    public ListResponse<Address> findAllAddresses(@RequestParam Long uid) {
        return this.shoppingService.findAddress(uid);
    }

    @DeleteMapping("/address")
    public BaseResponse deleteAddress(@RequestParam Long id) {
        return this.shoppingService.deleteAddress(id);
    }

    @GetMapping("/create-order")
    public SimpleResponse<ShoppingOrder> createOrder(@RequestParam Long uid, @RequestParam String shoppingCartItemIds,
                                                       HttpServletRequest request) {
        String[] ids = shoppingCartItemIds.split(",");
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            idList.add(Long.parseLong(ids[i]));
        }
        HttpSession session = request.getSession();
        String userName = session.getAttribute("username").toString();
        return this.shoppingService.createOrder(userName, idList);
    }

    @GetMapping("/settlement")
    public SimpleResponse<SettlementModel> settlementModel(@RequestParam Long orderId) {
        return this.shoppingService.startSettlement(orderId);
    }

    @PostMapping("/settlement")
    public BaseResponse settlement(@RequestBody IdRequest idRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = session.getAttribute("username").toString();
        return this.shoppingService.settlement(userName, idRequest.getId());
    }

    @PostMapping("/recharge")
    public SimpleResponse<Long> recharge(@RequestBody RechargeRequest rechargeRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = session.getAttribute("username").toString();
        return this.shoppingService.recharge(userName, rechargeRequest.getNumber(), rechargeRequest.getCode());
    }
}
