package com.iprzd.zshop.config;

import com.iprzd.zshop.repository.UserRepository;
import com.iprzd.zshop.service.CustomUserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 启用方法安全设置
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private CustomUserService customUserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(CustomUserService customUserService,
                             UserRepository userRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customUserService = customUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/",
                        "/init",
                        "/static/**",
                        "/home/**",
                        "/front/**",
                        "/article/**",
                        "/commodity/**").permitAll()
                .antMatchers(HttpMethod.POST, "/signup", "/login").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/upload", "/image").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), this.customUserService));
        http.logout().logoutSuccessUrl("/");
    }

}
