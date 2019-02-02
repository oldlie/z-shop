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

    private static final long serialVersionUID = 1540187254407L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long deliveryInfoId;
    @Column(columnDefinition = "bigint default 0")
    private long totalPrice;
    @Column(columnDefinition = "bigint default 0")
    private long expressFee;
    @Column(columnDefinition = "bigint default 0")
    private long reductionFee;
    @Column(columnDefinition = "bigint default 0")
    private long actualPrice;
    @Column(columnDefinition = "int default 0 comment '0:创建；1：结算；2：取消'")
    private int status;
    private String consignee;
    private String phone;
    private String address;

    @OneToMany(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<ShoppingRecord> records;
}
