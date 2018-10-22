package com.iprzd.zshop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_pay_card_log")
@Setter
@Getter
public class PayCardLogEntity implements Serializable {

    private static final long serialVersionUID = 1540189307650L;

    @Id @GeneratedValue
    private long id;
    private long operationUid;
    private String operationAccount;
    private String operation;
    private Date createAt;
}