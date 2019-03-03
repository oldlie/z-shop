package com.iprzd.zshop.model;

import java.util.Date;
import java.util.Set;

import com.iprzd.zshop.entity.ShoppingRecord;
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
    private Set<ShoppingRecord> records;
    private long totalPrice;
}