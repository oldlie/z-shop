package com.iprzd.zshop.http.request.admin;

import com.iprzd.zshop.http.request.ListRequest;

public class ListByTitleRequest extends ListRequest {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
