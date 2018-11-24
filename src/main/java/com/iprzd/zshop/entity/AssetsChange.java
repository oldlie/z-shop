package com.iprzd.zshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_assets_change")
@Setter
@Getter
public class AssetsChange implements Serializable {

    private final static long serialVersionUID = 1540128117909L;

    @Id @GeneratedValue
    private long id;
    private long uid;
    private long total;
    private long value;
    private int isIncrement;
    private long orderId;
    private String comment;
    private Date createDate;
}
