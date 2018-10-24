package com.iprzd.zshop.controller.admin;

import java.util.List;

import com.iprzd.zshop.entity.PayCardEntity;
import com.iprzd.zshop.http.request.PageableRequest;
import com.iprzd.zshop.http.request.PayCardRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.PageResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.service.FinanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller("/admin/finance")
@RestController
public class FinanceController {

    private FinanceService financeService;

    @Autowired
    public void setFinanceService(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/paycards")
    public PageResponse<PayCardEntity> listPayCards(@RequestParam int page, @RequestParam int size,
            @RequestParam String orderBy, @RequestParam String order) {
        PayCardEntity entity = new PayCardEntity();
        PageableRequest<PayCardEntity> request = new PageableRequest<>(entity, page, size, orderBy, order);
        return this.financeService.payCards(request);
    }

    @PostMapping("/paycards")
    public SimpleResponse<List<PayCardEntity>> createPayCards(@RequestBody PayCardRequest request) {
        return this.financeService.createPayCards(request);
    }

    @PutMapping("/paycard")
    public BaseResponse editPayCard(@RequestBody PayCardRequest request) {
        return this.financeService.editPayCard(request);
    }
}
