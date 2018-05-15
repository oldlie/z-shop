package com.iprzd.zshop.http.response.admin.commodity;

import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.entity.commodity.Menu;

import java.util.List;

public class MenuListResponse extends BaseResponse {
    private List<Menu> list;

    public List<Menu> getList() {
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }
}
