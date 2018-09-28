package com.iprzd.zshop.service;

import com.iprzd.zshop.entity.*;
import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.entity.commodity.Specification;
import com.iprzd.zshop.http.request.ShoppingCartRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.ListResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.repository.AddressRepository;
import com.iprzd.zshop.repository.ShoppingCartRepository;
import com.iprzd.zshop.repository.ShoppingOrderRepository;
import com.iprzd.zshop.repository.UserRepository;
import com.iprzd.zshop.repository.commodity.CommodityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {

    private AddressRepository addressRepository;
    private CommodityRepository commodityRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingOrderRepository shoppingOrderRepository;
    private UserRepository userRepository;

    public ShoppingService(AddressRepository addressRepository,
                           CommodityRepository commodityRepository,
                           ShoppingOrderRepository shoppingOrderRepository,
                           ShoppingCartRepository shoppingCartRepository,
                           UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.commodityRepository = commodityRepository;
        this.shoppingOrderRepository = shoppingOrderRepository;
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

    public SimpleResponse<ShoppingOrder> findShoppingOrderByUid(Long uid) {
        SimpleResponse<ShoppingOrder> response = new SimpleResponse<>();
        ShoppingOrder order = this.shoppingOrderRepository.findFirstByUserId(uid);
        response.setItem(order);
        return response;
    }

    public BaseResponse cancelShoppingOrder(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<ShoppingOrder> optionalShoppingOrder = this.shoppingOrderRepository.findById(id);
        if (optionalShoppingOrder.isPresent()) {
            ShoppingOrder order = optionalShoppingOrder.get();
            order.setStatus(2);
            this.shoppingOrderRepository.save(order);
        } else {
            response.setStatus(1);
            response.setMessage("订单不存在。");
        }
        return response;
    }

    public SimpleResponse<Address> findAddressByUid(Long uid) {
        SimpleResponse<Address> response = new SimpleResponse<>();
        Address address = this.addressRepository.findTopByUserIdAndIsDefault(uid, 1);
        response.setItem(address);
        return response;
    }

    public SimpleResponse<Long> storeAddress(Address address) {
        SimpleResponse<Long> response = new SimpleResponse<>();
        Optional<Address> optionalAddress = this.addressRepository.findById(address.getId());
        Address entity;
        if (optionalAddress.isPresent()) {
            entity = address;
        } else {
            entity = new Address();
        }
        entity.setUserId(address.getUserId());
        entity.setIsDefault(address.getIsDefault());
        entity.setProvince(address.getProvince());
        entity.setCity(address.getCity());
        entity.setCounty(address.getCounty());
        entity.setDetail(address.getDetail());
        entity.setContactName(address.getContactName());
        entity.setPhone(address.getPhone());
        entity = this.addressRepository.save(entity);
        response.setItem(entity.getId());
        return response;
    }
}
