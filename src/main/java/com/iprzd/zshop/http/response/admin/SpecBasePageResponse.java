package com.iprzd.zshop.http.response.admin;

import com.iprzd.zshop.http.response.BasePageResponse;
import com.iprzd.zshop.entity.commodity.Specification;

import java.util.List;

public class SpecBasePageResponse extends BasePageResponse {

    private List<Specification> list;

    public List<Specification> getList() {
        return list;
    }

    public void setList(List<Specification> list) {
        this.list = list;
    }
}
