package com.iprzd.zshop.controller.admin.response;

import com.iprzd.zshop.entity.commodity.Specification;

import java.util.List;

public class SpecPageResponse extends PageResponse {

    private List<Specification> list;

    public List<Specification> getList() {
        return list;
    }

    public void setList(List<Specification> list) {
        this.list = list;
    }
}
