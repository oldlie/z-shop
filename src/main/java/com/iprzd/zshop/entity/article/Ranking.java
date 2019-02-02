package com.iprzd.zshop.entity.article;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_article_ranking")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Ranking implements Serializable {
    private final static long serialVersionUID = 1541846733781L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long articleId;
    private long userId;
    private int ranking;
    private Date createAt;
}
