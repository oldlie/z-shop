package com.iprzd.zshop.service;

import com.iprzd.zshop.entity.ShoppingCart;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ShoppingCartRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.ListResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {

    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public SimpleResponse<Long> storeShoppingCart(ShoppingCartRequest request) {
        SimpleResponse<Long> response = new SimpleResponse<>();
        ShoppingCart shoppingCart;
        if (request.getId() > 0) {
            Optional<ShoppingCart> optionalShoppingCart = this.shoppingCartRepository.findById(request.getId());
            if (optionalShoppingCart.isPresent()) {
                shoppingCart = optionalShoppingCart.get();
            } else {
                shoppingCart = new ShoppingCart();
            }
        } else {
            shoppingCart = new ShoppingCart();
        }
        shoppingCart.setUid(request.getUid());
        shoppingCart.setCommodityId(request.getCommodityId());
        shoppingCart.setSpecId(request.getSpecId());
        shoppingCart.setCount(request.getCount());
        shoppingCart.setPrice(request.getPrice());
        shoppingCart.setCreateAt(new Date());
        shoppingCart = this.shoppingCartRepository.save(shoppingCart);
        response.setItem(shoppingCart.getId());
        return response;
    }

    public BaseResponse deleteShoppingCart(Long id) {
        BaseResponse response = new BaseResponse();
        this.shoppingCartRepository.deleteById(id);
        return response;
    }

    public ListResponse<ShoppingCart> findShoppingCartByUid(Long uid) {
        ListResponse<ShoppingCart> response = new ListResponse<>();
        List<ShoppingCart> list = this.shoppingCartRepository.findAllByUidOrderByIdDesc(uid);
        response.setList(list);
        return response;
    }
}
