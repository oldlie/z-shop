package com.iprzd.zshop.http.request.admin.commodity;

import com.iprzd.zshop.http.request.ListRequest;

public class SpecListByTitleRequest extends ListRequest {

    public SpecListByTitleRequest(int page, int size, String orderBy, int order, String title) {
        super(page, size, orderBy, order);
        this.title = title;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
