package com.iprzd.zshop.http.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class RechargeRequest implements Serializable {
    private final static long serialVersionUID = 1542983014124L;

    private String number;
    private String code;
}
