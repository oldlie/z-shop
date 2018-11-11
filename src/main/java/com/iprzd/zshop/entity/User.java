package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String userNickname;
    private String cellphone;
    private String cellphone2;
    private String resume;
    private String image;
    @Column(columnDefinition = "bigint default 0 comment '1厘'") // 厘 分 角 元
    private long money;
    @Column(columnDefinition = "tinyint default 0")
    private Integer isInit;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =
            @JoinColumn(name = "authority_id", insertable = false)
    )
    private List<Authority> authorities;

}

