package com.iprzd.zshop.entity.commodity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_commodity_menu")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Menu implements Serializable {
    private final static long serialVersionUID = 1541846733786L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long parentId;
    private int children;
    private String comment;
}
