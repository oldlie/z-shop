package com.iprzd.zshop.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PayCard {

    protected long id;
    protected int isValid;
    protected long accountId;
    protected String account;
    protected String note;
    protected Date createDate;
    protected int expiryMonth;
    protected String number;
    protected String verifyCode;
    protected int denomination;
    protected int amount;
    protected int isSoldOut;
    protected String customer;
    protected String customerPhone;
    protected int isUsed;
    protected String user;
    protected String userId;
    protected Date useDate;
}
