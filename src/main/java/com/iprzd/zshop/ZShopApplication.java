package com.iprzd.zshop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.MultipartConfigElement;

/**
 * 在VSCODE里运行需要先设置VSCODE项目，否则会提示在project中在不到mainClass
 */

@SpringBootApplication
public class ZShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZShopApplication.class, args);
    }

    /**
     * 密码编码器
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 跨域设置
     *
     * @return FilterRegistrationBean
     */
    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean simpleCorsFilter() {
        List<String> origins = new ArrayList<>();
        origins.add("http://localhost:4200");
        origins.add("http://localhost:4201");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(origins);
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


    @Value("${zs.fileTemp}")
    private String location;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        System.out.println(location);
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
