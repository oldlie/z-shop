package com.iprzd.zshop.controller;

import com.iprzd.zshop.entity.User;
import com.iprzd.zshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String hello() {
        return "{\"message\":\"hello\"}";
    }

    @PostMapping("/signup")
    public void signUp(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    class Token {
        public String token;
    }

}
