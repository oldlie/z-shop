package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javassist.SerialVersionUID;

import java.io.Serializable;

@Entity
@Table(name = "t_address")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Address implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    private long userId;
    private int isDefault;
    private String province;
    private String city;
    private String county;
    private String detail;
    private String contactName;
    private String phone;
}