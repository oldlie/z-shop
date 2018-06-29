package com.iprzd.zshop.http.response;

import com.iprzd.zshop.entity.commodity.CommodityImage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommodityImageListResponse extends BaseResponse {
    private List<CommodityImage> list;
}
