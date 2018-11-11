package com.iprzd.zshop.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_authority")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Authority implements Serializable {

    private final static long serialVersionUID = 1541846733776L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
}
