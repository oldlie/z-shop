package com.iprzd.zshop.entity.article;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_article_attachment")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Attachment implements Serializable {

    private final static long serialVersionUID = 1541846733776L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long articleId;
    private String url;

}
