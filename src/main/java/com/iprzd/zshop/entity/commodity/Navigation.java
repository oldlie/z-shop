package com.iprzd.zshop.entity.commodity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_commodity_navigation")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Navigation implements Serializable {
    private final static long serialVersionUID = 1541846733787L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long menuId;
    private String menuTitle;
    private int sequence;
}
