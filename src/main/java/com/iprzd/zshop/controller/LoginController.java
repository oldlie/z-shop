package com.iprzd.zshop.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static Gson gson = new Gson();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) {
        ResponseJson json = new ResponseJson();

        return gson.toJson(json);
    }
}
