package com.iprzd.zshop.entity.commodity;

import com.iprzd.zshop.entity.Tag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_commodity")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Commodity {

    @Id @GeneratedValue
    private long id;
    private String title;
    private String summary;
    private String image;
    private String comment;
    @Column(columnDefinition="text")
    private String description;
    @Column(columnDefinition="tinyint default 0")
    private int status;
    @Column(columnDefinition="int default 10")
    private int ranking;
    @Column(columnDefinition="int default 1")
    private int rankingCount;
    @Column(columnDefinition="int default 0")
    private int viewCount;
    @Column(columnDefinition="int default 0")
    private int shareCount;
    private Date createAt;
    private Date updateAt;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "t_commodity_tag",
            joinColumns = @JoinColumn(name = "commodity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "t_commodity_spec_map",
            joinColumns = @JoinColumn(name = "commodity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "spec_id", referencedColumnName = "id"))
    private List<Specification> specifications;

    // CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE CascadeType. REMOVE CascadeType. REMOVE
    @ManyToMany(cascade =  { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "t_commodity_menu_map",
            joinColumns = @JoinColumn(name = "commodity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"))
    @OrderBy(value = "id asc")
    private List<Menu> menus;
}
