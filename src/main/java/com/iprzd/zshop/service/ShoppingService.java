package com.iprzd.zshop.service;

import com.iprzd.zshop.entity.ShoppingCart;
import com.iprzd.zshop.entity.ShoppingCartItem;
import com.iprzd.zshop.entity.User;
import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.entity.commodity.Specification;
import com.iprzd.zshop.http.request.ShoppingCartRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.ListResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.repository.ShoppingCartRepository;
import com.iprzd.zshop.repository.UserRepository;
import com.iprzd.zshop.repository.commodity.CommodityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {

    private CommodityRepository commodityRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private UserRepository userRepository;

    public ShoppingService(CommodityRepository commodityRepository,
                           ShoppingCartRepository shoppingCartRepository,
                           UserRepository userRepository) {
        this.commodityRepository = commodityRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }

    public SimpleResponse<Long> storeShoppingCart(ShoppingCartRequest request) {
        SimpleResponse<Long> response = new SimpleResponse<>();
        ShoppingCart shoppingCart =
                this.shoppingCartRepository.findTopByUidAndCommodityIdAndSpecId(
                        request.getUid(),
                        request.getCommodityId(),
                        request.getSpecId());
        if (shoppingCart != null) {
            shoppingCart.setCount(shoppingCart.getCount() + request.getCount());
            shoppingCart.setPrice(shoppingCart.getPrice() + request.getPrice());
            this.shoppingCartRepository.save(shoppingCart);
            return response;

        }
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

    public ListResponse<ShoppingCartItem> findShoppingCartByUid(Long uid) {
        ListResponse<ShoppingCartItem> response = new ListResponse<>();
        List<ShoppingCart> list = this.shoppingCartRepository.findAllByUidOrderByIdDesc(uid);
        if (list == null || list.size() <= 0) {
            return response;
        }
        Optional<User> optionalUser = this.userRepository.findById(uid);
        if (!optionalUser.isPresent()) {
            response.setStatus(1);
            response.setMessage("请注册");
            return response;
        }
        User user = optionalUser.get();

        List<ShoppingCartItem> result = new ArrayList<>();
        for (ShoppingCart cart : list) {

            Optional<Commodity> optional = this.commodityRepository.findById(cart.getCommodityId());
            if (optional.isPresent()) {
                Commodity commodity = optional.get();
                List<Specification> specifications = commodity.getSpecifications();
                for (Specification specification : specifications) {
                    if (cart.getSpecId() == specification.getId()) {

                        ShoppingCartItem item = new ShoppingCartItem();
                        item.setShoppingCartId(cart.getId());
                        item.setUid(cart.getUid());
                        item.setUsername(user.getUsername());
                        item.setNickname(user.getUserNickname());
                        item.setCommodityId(commodity.getId());
                        item.setCommodityTitle(commodity.getTitle());
                        item.setCommoditySummary(commodity.getSummary());
                        item.setSpecId(specification.getId());
                        item.setSpecTitle(specification.getTitle());
                        item.setPrice(specification.getPrice());
                        item.setTypes(specification.getTypes());
                        item.setCount(cart.getCount());

                        result.add(item);
                    }
                }
            }

        }
        response.setList(result);
        return response;
    }
}
