package com.iprzd.zshop.http.request.admin.commodity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommodityRequest implements Serializable {
    private long id;
    private String title;
    private String summary;
    private String image;
    private String comment;
    private String description;
    private int status;
    private String menus;
    private String tags;
    private String specifications;
    private String images;

    private static final long serialVersionUID = 1L;
}
