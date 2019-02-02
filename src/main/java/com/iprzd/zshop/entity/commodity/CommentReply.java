package com.iprzd.zshop.entity.commodity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_commodity_comment_reply")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CommentReply implements Serializable {
    private final static long serialVersionUID = 1541846733783L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long commentId;
    private long userId;
    private String userNickname;
    private String comment;
    private Date createAt;
}
