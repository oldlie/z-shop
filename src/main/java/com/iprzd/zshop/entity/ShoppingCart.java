package com.iprzd.zshop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_shopping_cart")
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class ShoppingCart implements Serializable {

    private final static long serialVersionUID = 20180809234445L;

    @Id @GeneratedValue
    private long id;
    private long uid;
    private long commodityId;
    private long specId;
    private int count;
    private int price;
    private Date createAt;
}
