package com.iprzd.zshop.http.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyCodeRequest implements Serializable {

    private final static long serialVersionUID = 6L;

    private String cellphone;
}