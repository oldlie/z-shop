package com.iprzd.zshop.entity.commodity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_commodity_image")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CommodityImage implements Serializable {

    private static final long serialVersionUID = 1540188301152L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long commodityId;
    private String imagePath;
}
