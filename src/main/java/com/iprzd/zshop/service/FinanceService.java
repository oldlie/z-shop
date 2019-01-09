package com.iprzd.zshop.service;

import com.iprzd.zshop.entity.OrderCount;
import com.iprzd.zshop.entity.PayCard;
import com.iprzd.zshop.entity.PayCardLogEntity;
import com.iprzd.zshop.entity.User;
import com.iprzd.zshop.http.request.PageableRequest;
import com.iprzd.zshop.http.request.PayCardRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.PageResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.repository.OrderCountRepository;
import com.iprzd.zshop.repository.PayCardLogRepository;
import com.iprzd.zshop.repository.PayCardRepository;
import com.iprzd.zshop.repository.UserRepository;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FinanceService {

    private OrderCountRepository orderCountRepository;
    private PayCardRepository payCardRepository;
    private PayCardLogRepository payCardLogRepository;
    private UserRepository userRepository;

    public FinanceService(OrderCountRepository orderCountRepository, PayCardRepository payCardRepository,
            PayCardLogRepository payCardLogRepository, UserRepository userRepository) {
        this.orderCountRepository = orderCountRepository;
        this.payCardRepository = payCardRepository;
        this.payCardLogRepository = payCardLogRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public SimpleResponse<List<PayCard>> createPayCards(String username, PayCardRequest request) {
        SimpleResponse<List<PayCard>> response = new SimpleResponse<>();

        if (request.getId() > 0) {
            Optional<PayCard> optionalPayCard = this.payCardRepository.findById(request.getId());
            if (optionalPayCard.isPresent()) {
                PayCard target = optionalPayCard.get();
                target.setNote(request.getNote());
                target.setAmount(request.getAmount());
                target.setIsSoldOut(request.getIsSoldOut());
                target.setCustomer(request.getCustomer());
                target.setCustomerPhone(request.getCustomerPhone());
                this.payCardRepository.save(target);
            } else {
                response.setStatus(1);
                response.setMessage("Pay card does not exist.");
            }
            return response;
        }

        List<OrderCount> orderCounts = this.orderCountRepository.findAll(Sort.by(Sort.Order.desc("id")));
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int _start = 1;
        if (orderCounts.size() > 0) {
            OrderCount orderCount = orderCounts.get(0);
            if (year == orderCount.getYear() && month == orderCount.getMonth()) {
                _start = orderCount.getCount() + 1;
            }
        }

        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            response.setStatus(1);
            response.setMessage("Create pay card: user is not exist.");
            return response;
        }

        List<PayCard> _list = new ArrayList<>();
        List<PayCardLogEntity> _logs = new ArrayList<>();

        for (int i = 0; i < request.getCount(); i++, _start++) {
            String _numberStr = String.format("%06d", _start);
            if (_numberStr.endsWith("4")) {
                _start++;
                _numberStr = String.format("%06d", _start);
            }
            String _serial = request.getPrefix() + String.valueOf(year) + String.valueOf(month) + String.format("%06d", _numberStr);
            String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
            PayCard payCard = new PayCard();
            payCard.setAccountId(user.getId());
            payCard.setAccount(user.getUsername());
            payCard.setNote(request.getNote());
            payCard.setCreateDate(calendar.getTime());
            payCard.setExpiryMonth(request.getExpiryMonth());
            payCard.setNumber(_serial);
            payCard.setVerifyCode(uuid.substring(0, 12));
            payCard.setDenomination(request.getDenomination());
            payCard.setAmount(request.getAmount());
            payCard.setCustomer(request.getCustomer());
            payCard.setCustomerPhone(request.getCustomerPhone());
            payCard.setIsSoldOut(request.getIsSoldOut());
            payCard.setIsValid(1);
            _list.add(payCard);

            PayCardLogEntity logEntity = new PayCardLogEntity();
            logEntity.setOperationUid(user.getId());
            logEntity.setOperationAccount(user.getUsername());
            logEntity.setCreateAt(calendar.getTime());
            logEntity.setOperation("添加卡片");
            _logs.add(logEntity);
        }

        List<PayCard> list = this.payCardRepository.saveAll(_list);
        response.setItem(list);

        this.payCardLogRepository.saveAll(_logs);

        OrderCount _orderCount = new OrderCount();
        _orderCount.setYear(year);
        _orderCount.setMonth(month);
        _orderCount.setCount(_start);
        this.orderCountRepository.save(_orderCount);

        return response;
    }

    @Transactional
    public BaseResponse editPayCard(String username, PayCardRequest request) {
        BaseResponse response = new BaseResponse();
        Optional<PayCard> optional = this.payCardRepository.findById(request.getId());
        if (!optional.isPresent()) {
            response.setStatus(1);
            response.setMessage("Edit pay card: card does not exist.");
            return response;
        }
        PayCard entity = optional.get();
        entity.setNote(request.getNote());

        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            response.setStatus(1);
            response.setMessage("Create pay card: user is not exist.");
            return response;
        }

        PayCardLogEntity logEntity = new PayCardLogEntity();
        logEntity.setOperation(request.toString());

        return response;
    }

    @Transactional
    public BaseResponse invalidPayCard(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<PayCard> optional = this.payCardRepository.findById(id);
        if (!optional.isPresent()) {
            response.setStatus(1);
            response.setMessage("Invalid pay card: cad does not exist.");
            return response;
        }
        PayCard payCard = optional.get();
        if (payCard.getIsUsed() == 1 || payCard.getIsSoldOut() == 1) {
            response.setStatus(1);
            response.setMessage("Invalid pay card: cad has been used/sold out.");
            return response;
        }
        payCard.setIsValid(0);
        this.payCardRepository.save(payCard);
        return response;
    }

    @Transactional
    public BaseResponse usePayCard(Long uid, String serialNumber, String verifyCode) {
        BaseResponse response = new BaseResponse();
        Optional<User> actualUserOpt = this.userRepository.findById(uid);
        if (!actualUserOpt.isPresent()) {
            response.setStatus(1);
            response.setMessage("use pay card: user does not exist.");
            return response;
        }
        User user = actualUserOpt.get();

        PayCard query = new PayCard();
        query.setNumber(serialNumber);
        query.setVerifyCode(verifyCode);
        Example<PayCard> example = Example.of(query);

        List<PayCard> optional = this.payCardRepository.findAll(example);
        if (optional.size() <= 0) {
            response.setStatus(1);
            response.setMessage("use pay card: card does not exist.");
            return response;
        } else if (optional.size() > 1) {
            response.setStatus(1);
            response.setMessage("use pay card: card duplication.");
            return response;
        }
        Calendar calendar = Calendar.getInstance();
        PayCard entity = optional.get(0);
        entity.setIsUsed(1);
        entity.setUserId(user.getId());
        entity.setUser(user.getUserNickname());
        entity.setUseDate(calendar.getTime());

        long money = user.getMoney();
        long denomination = entity.getDenomination();
        if (denomination <= 0) {
            response.setStatus(1);
            response.setMessage("use pay card: denomination is negative.");
            return response;
        }

        user.setMoney(money + denomination);
        this.userRepository.save(user);

        PayCardLogEntity log = new PayCardLogEntity();
        StringBuilder sb = new StringBuilder();
        sb.append("used:").append(uid);
        log.setOperation("use");

        return response;
    }

    public PageResponse<PayCard> myPayCards(Long uid, int page, int size, String orderBy, String order) {
        PayCard entity = new PayCard();
        entity.setUserId(uid);
        PageableRequest<PayCard> request = new PageableRequest<>(entity, page, size, orderBy, order);
        return this.payCards(request);
    }

    public PageResponse<PayCard> payCards(PageableRequest<PayCard> request) {
        PageResponse<PayCard> response = new PageResponse<>();

        /*
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withMatcher("account", match -> match.contains())
            .withMatcher("note", match -> match.contains())
            .withMatcher("customer", match -> match.contains())
            .withMatcher("customerPhone", match -> match.contains());

        Example<PayCard> example = Example.of(request.getEntity(), matcher);
        */

        Pageable pageable = request.pageable();
        Page<PayCard> payCardPage = this.payCardRepository.findAll(pageable);
        // Page<PayCard> payCardPage = this.payCardRepository.findAllByIsValid(1, pageable);
        response.setList(payCardPage.getContent());
        response.setTotal(payCardPage.getTotalElements());
        return response;
    }
}
