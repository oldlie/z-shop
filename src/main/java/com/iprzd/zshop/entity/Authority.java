package com.iprzd.zshop.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_authority")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Authority {
    @Id @GeneratedValue
    private long id;
    private String role;
}
