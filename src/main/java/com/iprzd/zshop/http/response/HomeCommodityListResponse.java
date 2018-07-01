package com.iprzd.zshop.http.response;

import com.iprzd.zshop.entity.commodity.CommodityInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class HomeCommodityListResponse extends BaseResponse {
    @Getter
    @Setter
    private List<CommodityInfo> list;
}
