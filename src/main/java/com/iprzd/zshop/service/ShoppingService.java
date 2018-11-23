package com.iprzd.zshop.service;

import com.iprzd.zshop.entity.*;
import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.entity.commodity.Specification;
import com.iprzd.zshop.http.request.ShoppingCartRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.ListResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.model.SettlementModel;
import com.iprzd.zshop.repository.*;
import com.iprzd.zshop.repository.commodity.CommodityRepository;
import com.iprzd.zshop.repository.commodity.SpecificationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class ShoppingService {

    private AddressRepository addressRepository;
    private CommodityRepository commodityRepository;
    private SettlementRepository settlementRepository;
    private SettlementCartMapRepository settlementCartMapRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingOrderRepository shoppingOrderRepository;
    private SpecificationRepository specificationRepository;
    private UserRepository userRepository;

    @Value("${zs.settlementLimit}")
    private int settlementLimit;

    public ShoppingService(AddressRepository addressRepository, CommodityRepository commodityRepository,
            SettlementRepository settlementRepository, SettlementCartMapRepository settlementCartMapRepository,
                           ShoppingOrderRepository shoppingOrderRepository,
            ShoppingCartRepository shoppingCartRepository, SpecificationRepository specificationRepository,
                           UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.commodityRepository = commodityRepository;
        this.settlementRepository = settlementRepository;
        this.settlementCartMapRepository = settlementCartMapRepository;
        this.shoppingOrderRepository = shoppingOrderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.specificationRepository = specificationRepository;
        this.userRepository = userRepository;
    }

    public SimpleResponse<Long> storeShoppingCart(ShoppingCartRequest request) {
        SimpleResponse<Long> response = new SimpleResponse<>();
        ShoppingCart shoppingCart = this.shoppingCartRepository.findTopByUidAndCommodityIdAndSpecId(request.getUid(),
                request.getCommodityId(), request.getSpecId());
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
        shoppingCart.setSpecification(request.getSpecification());
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

    public SimpleResponse<Address> findDefaultAddressByUid(Long uid) {
        SimpleResponse<Address> response = new SimpleResponse<>();
        Address address = this.addressRepository.findTopByUserIdAndIsDefault(uid, 1);
        response.setItem(address);
        return response;
    }

    public ListResponse<Address> findAddress(Long uid) {
        ListResponse<Address> response = new ListResponse<>();
        List<Address> addressList = this.addressRepository.findAllByUserIdOrderByIdDesc(uid);
        response.setList(addressList);
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

        if (address.getIsDefault() == 1) {
            Address defaultAddress = this.addressRepository.findTopByUserIdAndIsDefault(address.getUserId(), 1);
            if (defaultAddress != null) {
                defaultAddress.setIsDefault(0);
                this.addressRepository.save(defaultAddress);
            }
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

    public BaseResponse deleteAddress(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<Address> optional = this.addressRepository.findById(id);
        if (optional.isPresent()) {
            Address address = optional.get();
            this.addressRepository.delete(optional.get());
            if (address.getIsDefault() == 1) {
                Address defaultAddress = this.addressRepository.findTopByUserIdOrderByIdDesc(address.getUserId());
                if (defaultAddress != null) {
                    defaultAddress.setIsDefault(1);
                    this.addressRepository.save(defaultAddress);
                }
            }
        }
        return response;
    }

    public SimpleResponse<SettlementModel> createOrder(String username, List<Long> shoppingCartIdList) {
        SimpleResponse<SettlementModel> response = new SimpleResponse<>();

        User user = this.userRepository.findByUsername(username);
        Long uid = user.getId();

        Address address = this.addressRepository.findTopByUserIdAndIsDefault(uid, 1);

        ShoppingOrder shoppingOrder = new ShoppingOrder();
        shoppingOrder.setUserId(uid);
        if (address != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append(address.getProvince()).append(address.getCity()).append(address.getCounty())
                    .append(address.getDetail());
            shoppingOrder.setDeliveryInfoId(address.getId());
            shoppingOrder.setConsignee(address.getContactName());
            shoppingOrder.setPhone(address.getPhone());
            shoppingOrder.setAddress(sb.toString());
        }

        long totalPrice = 0;
        List<ShoppingRecord> shoppingRecords = new ArrayList<>();
        List<ShoppingCart> shoppingCarts = this.shoppingCartRepository.findAllById(shoppingCartIdList);
        for (ShoppingCart cart: shoppingCarts) {
            Optional<Commodity> optionalCommodity = this.commodityRepository.findById(cart.getCommodityId());
            if (optionalCommodity.isPresent()) {
                totalPrice = totalPrice + cart.getCount() * cart.getPrice();
                Commodity commodity = optionalCommodity.get();
                ShoppingRecord record = new ShoppingRecord();
                record.setCommodityId(commodity.getId());
                record.setCommodityImage(commodity.getImage());
                record.setCommodityCount(cart.getCount());
                record.setCommoditySummary(commodity.getSummary());
                record.setActualPrice(cart.getCount() * cart.getPrice());
                record.setCommodityTitle(commodity.getTitle());
                record.setSpecId(cart.getSpecId());
                record.setSpecification(cart.getSpecification());
            }
        }

        return response;
    }

    @Transactional
    public SimpleResponse<SettlementModel> createSettlement(String username, List<Long> shoppingCartItemIdList) {
        SimpleResponse<SettlementModel> response = new SimpleResponse<>();

        Settlement settlement = new Settlement();
        Address address;
        long totalPrice = 0;
        List<ShoppingCart> list;
        try {
            User user = this.userRepository.findByUsername(username);
            Long uid = user.getId();

            settlement.setStartDate(new Date());
            settlement.setLimitMinute(this.settlementLimit);
            address = this.addressRepository.findTopByUserIdAndIsDefault(uid, 1);
            if (address != null) {
                settlement.setConsignee(address.getContactName());
                settlement.setCellphone(address.getPhone());
                settlement.setProvince(address.getProvince());
                settlement.setCity(address.getCity());
                settlement.setCounty(address.getCounty());
                settlement.setAddress(address.getDetail());
            }

            list = this.shoppingCartRepository.findAllById(shoppingCartItemIdList);
            List<SettlementCartMap> _list  = new ArrayList<>();
            for (ShoppingCart item : list) {
                totalPrice = totalPrice + item.getPrice();
            }
            settlement.setTotalPrice(totalPrice);
            settlement = this.settlementRepository.save(settlement);

            for (ShoppingCart item : list) {
                SettlementCartMap _item = new SettlementCartMap();
                _item.setSettlementId(settlement.getId());
                _item.setCartId(item.getId());
                _list.add(_item);
            }
            this.settlementCartMapRepository.saveAll(_list);
        } catch (Exception e) {
            response.setStatus(1);
            response.setMessage(e.getMessage());
            return response;
        }

        SettlementModel model = new SettlementModel();
        model.setStartDate(settlement.getStartDate());
        model.setSettlementId(settlement.getId());
        model.setLimitMinute(settlement.getLimitMinute());
        if (address != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append(address.getProvince()).append(address.getCity()).append(address.getCounty())
                    .append(address.getDetail());
            model.setHasAddress(1);
            model.setAddress(sb.toString());
        } else {
            model.setHasAddress(0);
        }
        model.setConsignee(address.getContactName());
        model.setCellphone(address.getPhone());
        model.setItems(list);
        model.setTotalPrice(totalPrice);

        response.setItem(model);
        return response;
    }

    /**
     *
     * 1. Any Exception
     * 2. Order does not exist.
     * 3. Specification does not exist.
     *
     * @param username
     * @param id
     * @return response
     */
    @Transactional
    public BaseResponse settlement(String username, Long id) {
        BaseResponse response = new BaseResponse();
        try {
            User user = this.userRepository.findByUsername(username);
            long balance = user.getMoney();
            Optional<Settlement> optional = this.settlementRepository.findById(id);
            if (!optional.isPresent()) {
                Settlement settlement = optional.get();
                List<SettlementCartMap> maps = this.settlementCartMapRepository.findAllBySettlementId(id);
                List<Long> _idList = new ArrayList<>();
                for (SettlementCartMap _item : maps) {
                    _idList.add(_item.getId());
                }
                List<ShoppingCart> _itemList = this.shoppingCartRepository.findAllById(_idList);

                for (ShoppingCart cart : _itemList) {
                    Optional<Specification> optional1 = this.specificationRepository.findById(cart.getSpecId());
                    if (optional1.isPresent()) {
                        Specification specification = optional1.get();
                        int inventory = specification.getInventory() - cart.getCount();
                        if (inventory > 0) {
                            specification.setInventory(inventory);
                            this.specificationRepository.save(specification);
                        } else {
                            response.setStatus(3);
                            response.setMessage("Inventory is not enough.");
                            return response;
                        }
                    } else {
                        response.setStatus(3);
                        response.setMessage("Specification does not exist.");
                        return response;
                    }
                }



            } else {
                response.setStatus(2);
                response.setMessage("Order does not exist.");
                return response;
            }
        } catch (Exception e) {
            response.setStatus(1);
            response.setMessage(e.getMessage());
            return response;
        }

        return response;
    }
}
