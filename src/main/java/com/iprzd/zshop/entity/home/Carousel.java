package com.iprzd.zshop.entity.home;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_home_carousel")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Carousel {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String url;
    private String image;
    private int sequence;
    private String color;

}
