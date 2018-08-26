package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_shopping_record")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ShoppingRecord implements Serializable {

    @Id @GeneratedValue
    private long id;
    private long commodityId;
    private String commodityTitle;
    private String commodityImage;
    private String commoditySummary;
    private String actualPrice;
    private int commodityCount;
    @Column(columnDefinition = "tinyint comment '0:初始状态；1：未支付状态；2：待收货状态；3：取消状态；4：完成状态；5：售后状态；6：取消并退款'")
    private int status;
}
