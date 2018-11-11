package com.iprzd.zshop.entity.article;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_article_agree")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Agree implements Serializable {

    private final static long serialVersionUID = 1541846733775L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long articleId;
    private long userId;
    private Date createAt;
}
