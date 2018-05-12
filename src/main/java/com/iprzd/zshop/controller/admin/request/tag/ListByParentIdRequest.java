package com.iprzd.zshop.controller.admin.request.tag;

import com.iprzd.zshop.controller.admin.request.ListRequest;

public class ListByParentIdRequest extends ListRequest {
    private long parentId;

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
