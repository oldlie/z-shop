package com.iprzd.zshop.http.request.admin;

import com.iprzd.zshop.http.request.ListRequest;

public class ListByParentIdRequest extends ListRequest {
    private long parentId;

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
