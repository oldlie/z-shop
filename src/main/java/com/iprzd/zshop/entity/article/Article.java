package com.iprzd.zshop.entity.article;

import com.iprzd.zshop.entity.Tag;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_article")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Article implements Serializable {

    private final static long serialVersionUID = 1541846733777L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String summary;
    private String imageUrl;
    private String author;
    private long authorId;
    @Type(type = "text")
    private String content;
    @Column(columnDefinition="int default 0")
    private int status;
    @Column(columnDefinition="int default 0")
    private int viewCount;
    @Column(columnDefinition="int default 0")
    private int agreeCount;
    @Column(columnDefinition="int default 0")
    private int shareCount;
    @Column(columnDefinition="tinyint default 0")
    private int allowComment;
    @Column(columnDefinition="int default 10")
    private int ranking;
    @Column(columnDefinition="int default 1")
    private int rankingCount;
    private Date createAt;
    private Date updateAt;
    private Date publishAt;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "t_article_tag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(cascade =  { CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "t_article_menu_map",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    @OrderBy(value = "sequence asc")
    private List<Menu> menus;
}
