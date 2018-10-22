package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_settlement")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Settlement implements Serializable {

    private final static long serialVersionUID = 1539866193061L;

    @Id @GeneratedValue
    private long id;
    private Date startDate;
    private int limitMinute;
    @Column(columnDefinition = "tinyint comment '0:没有默认地址；1:有默认地址'")
    private int hasAddress;
    private String consignee;
    private String cellphone;
    private String province;
    private String city;
    private String county;
    private String address;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "t_settlement_cart",
            joinColumns = @JoinColumn(name = "settlement_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id"))
    private List<ShoppingCart> items;
    private BigDecimal totalPrice;

}
