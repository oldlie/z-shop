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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private long totalPrice;
}
