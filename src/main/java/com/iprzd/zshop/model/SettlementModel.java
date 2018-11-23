package com.iprzd.zshop.model;

import java.util.Date;
import java.util.List;

import com.iprzd.zshop.entity.ShoppingCart;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SettlementModel {

    private Date startDate;
    private long settlementId;
    private int limitMinute;
    private int hasAddress;
    private String consignee;
    private String cellphone;
    private String address;
    private List<ShoppingCart> items;
    private long totalPrice;
}