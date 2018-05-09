package com.iprzd.zshop.controller.response;

import com.iprzd.zshop.entity.commodity.Menu;

import java.util.List;

public class CommodityMenuListResponse extends BaseResponse {
    private List<Menu> list;

    public List<Menu> getList() {
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }
}
