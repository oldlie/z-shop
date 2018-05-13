package com.iprzd.zshop.controller.admin.request.commodity;

import com.iprzd.zshop.controller.admin.request.ListRequest;

public class SpecListByTitleRequest extends ListRequest {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
