package com.iprzd.zshop.entity.commodity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_commodity_comment")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Comment implements Serializable {
    private final static long serialVersionUID = 1541846733782L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long productId;
    private long userId;
    private String userNickname;
    private String comment;
    private int replyCount;
    private Date createAt;
}
