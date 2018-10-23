package com.iprzd.zshop.service;

import com.iprzd.zshop.entity.OrderCount;
import com.iprzd.zshop.entity.PayCardEntity;
import com.iprzd.zshop.entity.PayCardLogEntity;
import com.iprzd.zshop.entity.User;
import com.iprzd.zshop.http.request.PageableRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.PageResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.repository.OrderCountRepository;
import com.iprzd.zshop.repository.PayCardLogRepository;
import com.iprzd.zshop.repository.PayCardRepository;
import com.iprzd.zshop.repository.UserRepository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
    public SimpleResponse<List<PayCardEntity>> createPayCards(Long uid, int count, String node, int denomination,
            int expiryMonth, int amount, String customer, String customerPhone) {
        SimpleResponse<List<PayCardEntity>> response = new SimpleResponse<>();
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

        Optional<User> optional = this.userRepository.findById(uid);
        if (!optional.isPresent()) {
            response.setStatus(1);
            response.setMessage("Create pay card: user is not exist.");
            return response;
        }

        User user = optional.get();

        List<PayCardEntity> _list = new ArrayList<>();
        List<PayCardLogEntity> _logs = new ArrayList<>();

        for (int i = 0; i < count; i++, _start++) {
            String _serial = String.valueOf(year) + String.valueOf(month) + String.format("%06d", _start);
            String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
            PayCardEntity payCardEntity = new PayCardEntity();
            payCardEntity.setAccountId(user.getId());
            payCardEntity.setAccount(user.getUsername());
            payCardEntity.setNote(node);
            payCardEntity.setCreateDate(calendar.getTime());
            payCardEntity.setExpiryMonth(expiryMonth);
            payCardEntity.setNumber(_serial);
            payCardEntity.setVerifyCode(uuid.substring(0, 12));
            payCardEntity.setDenomination(denomination);
            payCardEntity.setAmount(amount);
            payCardEntity.setCustomer(customer);
            payCardEntity.setCustomerPhone(customerPhone);
            _list.add(payCardEntity);

            PayCardLogEntity logEntity = new PayCardLogEntity();
            logEntity.setOperationUid(user.getId());
            logEntity.setOperationAccount(user.getUsername());
            logEntity.setCreateAt(calendar.getTime());
            logEntity.setOperation("添加卡片");
            _logs.add(logEntity);
        }

        List<PayCardEntity> list = this.payCardRepository.saveAll(_list);
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
    public BaseResponse editPayCard(Long uid, Long id, String note, int expiryMonth, int amount, String customer,
            String customerPhone) {
        BaseResponse response = new BaseResponse();
        Optional<PayCardEntity> optional = this.payCardRepository.findById(id);
        if (!optional.isPresent()) {
            response.setStatus(1);
            response.setMessage("Edit pay card: card does not exist.");
            return response;
        }
        PayCardEntity entity = optional.get();
        entity.setNote(note);

        Optional<User> optional2 = this.userRepository.findById(uid);
        if (!optional2.isPresent()) {
            response.setStatus(1);
            response.setMessage("Create pay card: user is not exist.");
            return response;
        }

        PayCardLogEntity logEntity = new PayCardLogEntity();
        StringBuilder operationBuilder = new StringBuilder();
        operationBuilder.append("Edit:").append(id).append(",nt:").append(note).append(",em:,").append(expiryMonth)
                .append(",am").append(amount).append(",cu").append(customer).append(",cp").append(customerPhone);
        logEntity.setOperation(operationBuilder.toString());

        return response;
    }

    @Transactional
    public BaseResponse invalidPayCard(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<PayCardEntity> optional = this.payCardRepository.findById(id);
        if (!optional.isPresent()) {
            response.setStatus(1);
            response.setMessage("Invalid pay card: cad does not exist.");
            return response;
        }
        PayCardEntity payCardEntity = optional.get();
        if (payCardEntity.getIsUsed() == 1 || payCardEntity.getIsSoldOut() == 1) {
            response.setStatus(1);
            response.setMessage("Invalid pay card: cad has been used/sold out.");
            return response;
        }
        payCardEntity.setIsValid(0);
        this.payCardRepository.save(payCardEntity);
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

        PayCardEntity query = new PayCardEntity();
        query.setNumber(serialNumber);
        query.setVerifyCode(verifyCode);
        Example<PayCardEntity> example = Example.of(query);

        List<PayCardEntity> optional = this.payCardRepository.findAll(example);
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
        PayCardEntity entity = optional.get(0);
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

    public PageResponse<PayCardEntity> myPayCards(Long uid, int page, int size, String orderBy, String order) {
        PayCardEntity entity = new PayCardEntity();
        entity.setUserId(uid);
        PageableRequest<PayCardEntity> request = new PageableRequest<>(entity, page, size, orderBy, order);
        return this.payCards(request);
    }

    public PageResponse<PayCardEntity> payCards(PageableRequest<PayCardEntity> request) {
        PageResponse<PayCardEntity> response = new PageResponse<>();

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withMatcher("account", match -> match.contains())
            .withMatcher("note", match -> match.contains())
            .withMatcher("customer", match -> match.contains())
            .withMatcher("customerPhone", match -> match.contains());

        Example<PayCardEntity> example = Example.of(request.getEntity(), matcher);
        Page<PayCardEntity> payCardPage = this.payCardRepository.findAll(example, request.pageable());
        response.setList(payCardPage.getContent());
        response.setTotal(payCardPage.getTotalElements());
        return response;
    }
}
