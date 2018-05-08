package com.iprzd.zshop.controller;

import com.iprzd.zshop.controller.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @PostMapping("/store")
    public BaseResponse store() {
        BaseResponse response = new BaseResponse();
        return response;
    }
}
