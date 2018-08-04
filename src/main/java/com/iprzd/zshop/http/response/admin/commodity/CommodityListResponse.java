package com.iprzd.zshop.http.response.admin.commodity;

import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.http.response.PageResponse;

import java.util.List;

public class CommodityListResponse extends PageResponse {

    private final static long serialVersionUID = 2L;

    private List<Commodity> list;

    public List<Commodity> getList() {
        return list;
    }

    public void setList(List<Commodity> list) {
        this.list = list;
    }
}
