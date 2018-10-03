package com.iprzd.zshop.http.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ShoppingCartRequest implements Serializable {
    private long id;
    private long uid;
    private long commodityId;
    private long specId;
    private int count;
    private int price;
}
