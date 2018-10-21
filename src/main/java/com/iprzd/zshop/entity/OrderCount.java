package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_order_count")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderCount implements Serializable {
    private static final long serialVersionUID = 1540133553063L;

    @Id @GeneratedValue
    private long id;
    private int year;
    private int month;
    private int count;
}
