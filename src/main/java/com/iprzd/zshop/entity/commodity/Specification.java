package com.iprzd.zshop.entity.commodity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "t_commodity_spec")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Specification implements Serializable {

    @Id @GeneratedValue
    private long id;
    private String title;
    private long commodityId;
    private String breed;
    private String origin;
    private String feature;
    private String spec;
    private String store;
    private String productDatetime;
    private BigDecimal price;
    private int inventory;
    @Column(columnDefinition = "tinyint default 0 comment '0：实体商品；1：虚拟商品'")
    private int types;

}
