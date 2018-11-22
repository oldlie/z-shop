package com.iprzd.zshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_settlement_cart")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SettlementCartMap implements Serializable {
   
    private final static long serialVersionUID = 1542865560304L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long settlementId;
    private long cartId;
}