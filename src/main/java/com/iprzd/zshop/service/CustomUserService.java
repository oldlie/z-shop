package com.iprzd.zshop.service;

import com.iprzd.zshop.repository.AuthorityRepository;
import com.iprzd.zshop.repository.UserRepository;
import com.iprzd.zshop.repository.entity.Authorities;
import com.iprzd.zshop.repository.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CustomUserService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private AuthorityRepository authorityRepository;
    @Autowired
    public void setAuthorityRepository(AuthorityRepository repository) {
        this.authorityRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = this.userRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Authorities> authoritiesList = this.authorityRepository.findAllByUsername(username);
        for (Authorities authorities1 : authoritiesList) {
            authorities.add(new SimpleGrantedAuthority(authorities1.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(users.getUsername(),
                users.getPassword(), authorities);
    }


}
