package com.iprzd.zshop.entity.article;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_article_menu")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Menu implements Serializable {
    private final static long serialVersionUID = 1541846733780L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long parentId;
    private String comment;
    private int children;
    private int sequence;
}