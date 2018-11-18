package com.iprzd.zshop.http.request;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class PayCardRequest implements Serializable {

    private final static long serialVersionUID = 1540362525999L;

    private long id;
    private int count;
    private String note;
    private long denomination;
    private int expiryMonth;
    private long amount;
    private String customer;
    private String customerPhone;
    private int isSoldOut;

}