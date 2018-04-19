package com.iprzd.zshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ZShopApplication {

    public static void main(String[] args) {


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("admin");
        System.out.println(encode);

        SpringApplication.run(ZShopApplication.class, args);
    }
}
