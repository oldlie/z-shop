package com.iprzd.zshop.http.response;

import com.iprzd.zshop.entity.commodity.CommodityInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityInfoResponse extends BaseResponse {

    private CommodityInfo commodityInfo;
}
