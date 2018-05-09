package com.iprzd.zshop.entity.commodity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_commodity_spec")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Specification {
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
}
