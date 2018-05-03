package com.iprzd.zshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(String username, String password) {
        ResponseJson json = new ResponseJson();

        return "";
    }
}
