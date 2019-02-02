package com.iprzd.zshop.entity.home;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "t_home_carousel")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Carousel implements Serializable {

    private static final long serialVersionUID = 1540188082847L;

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String url;
    private String image;
    private int sequence;
    private String color;

}
