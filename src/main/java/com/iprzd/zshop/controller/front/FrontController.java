package com.iprzd.zshop.controller.front;

import javax.annotation.security.RolesAllowed;

import com.iprzd.zshop.entity.User;
import com.iprzd.zshop.http.request.ProfileRequest;
import com.iprzd.zshop.http.request.RegisterRequest;
import com.iprzd.zshop.http.request.VerifyCodeRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.service.FrontService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/front")
public class FrontController {

    private FrontService frontService;

    public FrontController(FrontService frontService) {
        this.frontService = frontService;
    }

    @PostMapping("/register")
    public BaseResponse register(@RequestBody RegisterRequest request) {
        BaseResponse response = new BaseResponse();
        if (!request.getCode().equals("hello")) {
            response.setStatus(1);
            response.setMessage("验证码不正确");
            return response;
        }
        return response;
    }

    @PostMapping("/signup")
    public BaseResponse signUp(@RequestBody ProfileRequest request) {
        return this.frontService.saveFrontUser(request.getUsername(), request.getPassword(), request.getUserNickname(),
                request.getCellphone(), request.getCellphone2(), request.getResume(), request.getImage());
    }

    @PostMapping("/code")
    public BaseResponse code(@RequestBody VerifyCodeRequest request) {
        return this.frontService.sendCode(request.getCellphone());
    }

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping("/profile")
    public BaseResponse profile(@RequestBody ProfileRequest request) {
        return this.frontService.saveFrontUser(request.getUsername(), request.getPassword(), request.getUserNickname(),
                request.getCellphone(), request.getCellphone2(), request.getResume(), request.getImage());
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/profile")
    public SimpleResponse<User> profile(@RequestParam String account) {
        return this.frontService.findUserByAccount(account);
    }
}