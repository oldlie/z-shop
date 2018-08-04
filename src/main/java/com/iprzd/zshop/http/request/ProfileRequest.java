package com.iprzd.zshop.http.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest implements Serializable {

    private final static long serialVersionUID = 5L;

    private String username;
    private String userNickname;
    private String cellphone;
    private String password;
    private String image;
}