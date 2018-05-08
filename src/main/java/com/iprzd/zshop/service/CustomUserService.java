package com.iprzd.zshop.service;

import com.iprzd.zshop.entity.Authority;
import com.iprzd.zshop.entity.User;
import com.iprzd.zshop.repository.AuthorityRepository;
import com.iprzd.zshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
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
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Authority authority : user.getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }


}
