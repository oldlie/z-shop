package com.iprzd.zshop.entity.home;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_home_commodity")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class HomeCommodity implements Serializable {
    @Id @GeneratedValue
    private long id;
    private long commodityId;
    private int sequence;
}
