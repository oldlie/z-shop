package com.iprzd.zshop.controller.admin;

import java.util.List;

import com.iprzd.zshop.entity.PayCard;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.PageableRequest;
import com.iprzd.zshop.http.request.PayCardRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.PageResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.service.FinanceService;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin/finance")
public class FinanceController {

    private FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/test")
    public BaseResponse test() {
        return new BaseResponse();
    }

    @GetMapping("/pay-card")
    public PageResponse<PayCard> listPayCards(@RequestParam int page, @RequestParam int size,
                                              @RequestParam String orderBy,
                                              @RequestParam String order) {
        PayCard entity = new PayCard();
        PageableRequest<PayCard> request = new PageableRequest<>(entity, page, size,
                orderBy, order);
        return this.financeService.payCards(request);
    }

    @PostMapping("/pay-card")
    public SimpleResponse<List<PayCard>> createPayCards(@RequestBody PayCardRequest request,
                                                        HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String userName = session.getAttribute("username").toString();
        return this.financeService.createPayCards(userName, request);
    }

    @PutMapping("/pay-card")
    public BaseResponse editPayCard(@RequestBody PayCardRequest request,
                                    HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        try {
            String username = session.getAttribute("username").toString();
            return this.financeService.editPayCard(username, request);
        } catch (Exception e) {
            BaseResponse response = new BaseResponse();
            response.setStatus(1);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    @DeleteMapping("/pay-card")
    public BaseResponse invalidPayCard(@RequestParam long id) {
        return this.financeService.invalidPayCard(id);
    }
}
