package com.iprzd.zshop.entity.commodity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_commodity_ranking")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Ranking implements Serializable {
    private final static long serialVersionUID = 1541846733788L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long productId;
    private long userId;
    private int ranking;
    private Date createAt;
}
