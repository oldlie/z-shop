package com.iprzd.zshop.http.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PageRequest implements Serializable {

    private final static long serialVersionUID = 1541848600411L;

    private int page;
    private int size;
    private String orderBy;
    private String order;

}
