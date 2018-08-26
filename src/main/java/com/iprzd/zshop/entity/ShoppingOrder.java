package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "t_shopping_order")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShoppingOrder implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private long userId;
    private long deliveryInfoId;
    private int totalPrice;
    private int expressFee;
    private int reductionFee;
    private int actualPrice;

    @OneToOne(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<ShoppingRecord> records;
}
