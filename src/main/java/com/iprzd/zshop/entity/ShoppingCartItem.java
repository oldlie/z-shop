package com.iprzd.zshop.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ShoppingCartItem implements Serializable {

    private static final long serialVersionUID = 1540187279589L;

    private long shoppingCartId;
    private long uid;
    private String username;
    private String nickname;
    private long commodityId;
    private String commodityTitle;
    private String commoditySummary;
    private long specId;
    private String specTitle;
    private BigDecimal price;
    private int types;
    private int count;
}
