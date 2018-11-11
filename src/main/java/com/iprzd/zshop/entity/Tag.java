package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_tag")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Tag implements Serializable {

    private final static long serialVersionUID = 1541829212272L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long parentId;
}
