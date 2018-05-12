package com.iprzd.zshop.controller;

import com.iprzd.zshop.controller.response.BaseResponse;
import com.iprzd.zshop.entity.Authority;
import com.iprzd.zshop.entity.User;
import com.iprzd.zshop.repository.AuthorityRepository;
import com.iprzd.zshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public IndexController(AuthorityRepository authorityRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/")
    public String index() {
        return "当前版本：v1.0.1";
    }

    @GetMapping("/init")
    public BaseResponse init() {
        BaseResponse response = new BaseResponse();

        Authority authority = new Authority();
        authority.setRole("ADMIN");
        this.authorityRepository.save(authority);

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);

        User admin = new User();
        admin.setUsername("admin@zshop.com");
        admin.setPassword(this.bCryptPasswordEncoder.encode("123456"));
        admin.setAuthorities(authorities);
        this.userRepository.save(admin);

        response.setStatus(0);
        response.setMessage("success");
        return response;
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
