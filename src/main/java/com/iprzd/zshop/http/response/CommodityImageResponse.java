package com.iprzd.zshop.http.response;

import com.iprzd.zshop.entity.commodity.CommodityImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityImageResponse extends BaseResponse {
    private CommodityImage image;
}
