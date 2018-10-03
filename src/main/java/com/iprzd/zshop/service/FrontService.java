package com.iprzd.zshop.service;

import java.util.ArrayList;
import java.util.List;

import com.iprzd.zshop.entity.Authority;
import com.iprzd.zshop.entity.User;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.SimpleResponse;
import com.iprzd.zshop.repository.AuthorityRepository;
import com.iprzd.zshop.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FrontService {

    private AuthorityRepository authorityRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    public FrontService(AuthorityRepository authorityRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
            UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public BaseResponse sendCode(String cellphone) {
        BaseResponse response = new BaseResponse();
        try {
            User user = this.userRepository.findOneByCellphone(cellphone);
            if (user != null) {
                response.setStatus(1);
                response.setMessage("这个号码已经注册过了");
                return response;
            }
        } catch (Exception e) {
            response.setStatus(1);
            response.setMessage(e.getLocalizedMessage());
        }
        
        // TODO: 这里哦，短信验证的具体代码了
        return response;
    }

    public BaseResponse saveFrontUser(String username, String password, String nickname, String cellphone,
            String cellphone2, String resume, String image) {
        BaseResponse response = new BaseResponse();

        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            response.setStatus(1);
            response.setMessage("这个账号已经注册了");
            return response;
        }

        Authority authority = this.authorityRepository.findOneByRole("USER");
        if (authority == null) {
            response.setStatus(1);
            response.setMessage("网站还未开放前端用户权限，请联系管理员");
            return response;
        }

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);

        user = new User();
        user.setUsername(username);
        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        user.setUserNickname(nickname);
        user.setCellphone(cellphone);
        user.setImage(image);
        user.setAuthorities(authorities);
        this.userRepository.save(user);

        return response;
    }

    public SimpleResponse<User> findUserByAccount(String account) {
        SimpleResponse<User> response = new SimpleResponse<>();
        User user = this.userRepository.findByUsername(account);
        response.setItem(user);
        return response;
    }
}