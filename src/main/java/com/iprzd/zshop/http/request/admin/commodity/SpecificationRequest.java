package com.iprzd.zshop.http.request.admin.commodity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class SpecificationRequest implements Serializable {
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
    private int types;
}
