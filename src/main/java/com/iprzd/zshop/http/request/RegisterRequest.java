package com.iprzd.zshop.http.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest implements Serializable {

    private final static long serialVersionUID = 3L;
    
    private String username;
    private String cellphone;
    private String code;
}