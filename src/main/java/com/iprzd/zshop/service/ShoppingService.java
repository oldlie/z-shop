package com.iprzd.zshop.service;

import com.iprzd.zshop.entity.*;
import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.entity.commodity.Specification;
import com.iprzd.zshop.http.request.PageableRequest;
import com.iprzd.zshop.http.request.ShoppingCartRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.ListResponse;
import com.iprzd.zshop.http.response.PageResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.model.SettlementModel;
import com.iprzd.zshop.repository.*;
import com.iprzd.zshop.repository.commodity.CommodityRepository;
import com.iprzd.zshop.repository.commodity.SpecificationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.transaction.Transactional;

@Service
public class ShoppingService {

    private AddressRepository addressRepository;
    private CommodityRepository commodityRepository;
    private PayCardRepository payCardRepository;
    private PayCardLogRepository payCardLogRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingOrderRepository shoppingOrderRepository;
    private ShoppingRecordRepository shoppingRecordRepository;
    private SpecificationRepository specificationRepository;
    private UserRepository userRepository;

    @Value("${zs.settlementLimit}")
    private int settlementLimit;

    public ShoppingService(AddressRepository addressRepository, CommodityRepository commodityRepository,
                           PayCardRepository payCardRepository, PayCardLogRepository payCardLogRepository,
                           ShoppingOrderRepository shoppingOrderRepository,
            ShoppingCartRepository shoppingCartRepository, ShoppingRecordRepository shoppingRecordRepository,
                           SpecificationRepository specificationRepository,
                           UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.commodityRepository = commodityRepository;
        this.payCardRepository = payCardRepository;
        this.payCardLogRepository = payCardLogRepository;
        this.shoppingOrderRepository = shoppingOrderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingRecordRepository = shoppingRecordRepository;
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
            shoppingCart = optionalShoppingCart.isPresent() ? optionalShoppingCart.get() : new ShoppingCart();
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

    /**
     * 创建订单
     * @param username
     * @param shoppingCartIdList
     * @return
     */
    @Transactional
    public SimpleResponse<ShoppingOrder> createOrder(String username, List<Long> shoppingCartIdList) {
        SimpleResponse<ShoppingOrder> response = new SimpleResponse<>();

        User user = this.userRepository.findByUsername(username);
        Long uid = user.getId();

        Address address = this.addressRepository.findTopByUserIdAndIsDefault(uid, 1);

        ShoppingOrder shoppingOrder = new ShoppingOrder();
        shoppingOrder.setIsValid(1);
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
        Set<ShoppingRecord> shoppingRecordSet = new HashSet<>();
        List<ShoppingCart> shoppingCarts = this.shoppingCartRepository.findAllById(shoppingCartIdList);
        for (ShoppingCart cart: shoppingCarts) {
            Optional<Commodity> optionalCommodity = this.commodityRepository.findById(cart.getCommodityId());
            Optional<Specification> optionalSpecification = this.specificationRepository.findById(cart.getSpecId());
            if (optionalCommodity.isPresent() && optionalSpecification.isPresent()) {
                totalPrice = totalPrice + cart.getCount() * cart.getPrice();
                Commodity commodity = optionalCommodity.get();
                Specification specification = optionalSpecification.get();
                ShoppingRecord record = new ShoppingRecord();
                record.setCommodityId(commodity.getId());
                record.setCommodityImage(commodity.getImage());
                record.setCommodityCount(cart.getCount());
                record.setCommoditySummary(commodity.getSummary());
                record.setActualPrice(cart.getCount() * cart.getPrice());
                record.setCommodityTitle(commodity.getTitle());
                record.setSpecId(cart.getSpecId());
                record.setSpecification(specification.getTitle());
                record.setStatus(0);
                shoppingRecordSet.add(record);
            }
        }
        shoppingOrder.setCreateDate(new Date());
        shoppingOrder.setTotalPrice(totalPrice);
        shoppingOrder.setStatus(0);
        shoppingOrder.setRecords(shoppingRecordSet);
        shoppingOrder = this.shoppingOrderRepository.save(shoppingOrder);
        response.setItem(shoppingOrder);
        return response;
    }

    /**
     * 开始结算，提供展示结算界面的信息
     *
     * 1. Order does not exist.
     *
     * @return
     */
    public SimpleResponse<SettlementModel> startSettlement(long orderId) {
        SimpleResponse<SettlementModel> response = new SimpleResponse<>();
        Optional<ShoppingOrder> optionalOrder = this.shoppingOrderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            ShoppingOrder shoppingOrder = optionalOrder.get();
            SettlementModel model = new SettlementModel();
            model.setStartDate(shoppingOrder.getCreateDate());
            model.setLimitMinute(this.settlementLimit);
            if (shoppingOrder.getDeliveryInfoId() > 0) {
                model.setHasAddress(1);
                model.setConsignee(shoppingOrder.getConsignee());
                model.setCellphone(shoppingOrder.getPhone());
                model.setAddress(shoppingOrder.getAddress());
            } else {
                model.setHasAddress(0);
            }
            model.setTotalPrice(shoppingOrder.getTotalPrice());
            model.setRecords(shoppingOrder.getRecords());
            response.setItem(model);
        } else {
            response.setStatus(1);
            response.setMessage("Order does not exist.");
        }
        return response;
    }

    /**
     * 1. Order does not exist.
     * 2. User does not exist.
     * 3. Money is not enough.
     *
     * @param orderId
     * @return
     */
    public BaseResponse settlement(String username, long orderId) {
        BaseResponse response = new BaseResponse();
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            response.setStatus(2);
            response.setMessage("User does not exist.");
            return response;
        }

        Optional<ShoppingOrder> optionalOrder = this.shoppingOrderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            response.setStatus(1);
            response.setMessage("Order does not exist.");
            return response;
        }

        ShoppingOrder shoppingOrder = optionalOrder.get();

        long balance = user.getMoney() - shoppingOrder.getTotalPrice();
        if (balance <= 0) {
            response.setStatus(3);
            response.setMessage("Money is not enough.");
            return response;
        }

        Set<ShoppingRecord> records = shoppingOrder.getRecords();
        for (ShoppingRecord record : records) {
            record.setStatus(2);
        }

        shoppingOrder.setStatus(1);

        this.shoppingOrderRepository.save(shoppingOrder);

        return response;
    }

    /**
     * 1. User does not exist.
     *
     * @param username
     * @return
     */
    public PageResponse<ShoppingOrder> listMyShoppingOrder(String username, int page, int size, String OrderBy,
                                                           String order) {
        PageResponse<ShoppingOrder> response = new PageResponse<>();

        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            response.setStatus(1);
            response.setMessage("User does not exist.");
            return response;
        }

        PageableRequest request = new PageableRequest(null, page, size, OrderBy, order);
        Page<ShoppingOrder> shoppingOrderPage = this.shoppingOrderRepository.findAllByUserId(request.pageable());

        response.setList(shoppingOrderPage.getContent());
        response.setTotal(shoppingOrderPage.getTotalElements());

        return response;
    }

    public PageResponse<ShoppingOrder> listShoppingOrder(int page, int size, String orderBy, String order) {
        PageResponse<ShoppingOrder> response = new PageResponse<>();

        PageableRequest<ShoppingOrder> request = new PageableRequest<>(null, page, size, orderBy, order);
        Page<ShoppingOrder> shoppingOrderPage = this.shoppingOrderRepository.findAll(request.pageable());

        response.setTotal(shoppingOrderPage.getTotalElements());
        response.setList(shoppingOrderPage.getContent());

        return response;
    }

    /**
     * 1. shopping record does not exist.
     * @param id
     * @return
     */
    public BaseResponse updateShoppingRecord(Long id, int status) {
        BaseResponse response = new BaseResponse();
        Optional<ShoppingRecord> optionalShoppingRecord = this.shoppingRecordRepository.findById(id);
        if (!optionalShoppingRecord.isPresent()) {
            response.setStatus(1);
            response.setMessage("shopping record does not exist.");
            return response;
        }

        ShoppingRecord record = optionalShoppingRecord.get();
        record.setStatus(status);
        this.shoppingRecordRepository.save(record);

        return response;
    }

    /**
     * 1. User does not e6xist.
     * 2. Pay card does not exist.
     *
     * @param username
     * @param number
     * @param code
     * @return
     */
    @Transactional
    public BaseResponse recharge(String username, String number, String code) {
        BaseResponse response = new BaseResponse();
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            response.setStatus(1);
            response.setMessage("User does not exist.");
            return response;
        }
        PayCard payCard = this.payCardRepository.findFirstByNumberAndVerifyCode(number, code);
        if (payCard == null) {
            response.setStatus(2);
            response.setMessage("Pay card does not exist.");
            return response;
        }

        if (payCard.getIsValid() == 0) {
            response.setStatus(3);
            response.setMessage("Pay card invalid.");
            return response;
        }

        if (payCard.getDenomination() <= 0) {
            response.setStatus(3);
            response.setMessage("Pay card has negative denomination.");
            return response;
        }

        Date useDate = new Date();

        payCard.setIsValid(0);
        payCard.setUserId(user.getId());
        payCard.setUseDate(useDate);
        payCard.setIsUsed(1);
        payCard.setUser(username);
        this.payCardRepository.save(payCard);

        long balance = user.getMoney() + payCard.getDenomination();
        user.setMoney(balance);
        this.userRepository.save(user);

        PayCardLogEntity logEntity = new PayCardLogEntity();
        logEntity.setCreateAt(useDate);
        logEntity.setOperation("recharge");
        logEntity.setOperationUid(user.getId());
        logEntity.setOperationAccount(username);
        this.payCardLogRepository.save(logEntity);

        return response;
    }
}
