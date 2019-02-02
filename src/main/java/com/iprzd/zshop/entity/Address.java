package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "t_address")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Address implements Serializable {

    private final static long serialVersionUID = 1540188041777L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
