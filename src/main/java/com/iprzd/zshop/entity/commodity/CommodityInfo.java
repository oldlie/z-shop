package com.iprzd.zshop.entity.commodity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CommodityInfo implements Serializable {
    private Commodity commodity;
    private List<CommodityImage> images;
}
