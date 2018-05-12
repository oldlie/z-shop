package com.iprzd.zshop.controller.admin.request;

import org.springframework.data.domain.Sort;

public class ListRequest {
    private int page;
    private int size;
    private String orderBy;
    private int order;

    public ListRequest() {}

    public ListRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public ListRequest(int page, int size, String orderBy, int order) {
        this.page = page;
        this.size = size;
        this.orderBy = orderBy;
        this.order = order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Sort getSort() {
        return Sort.by(
                this.order > 0 ? Sort.Direction.DESC : Sort.Direction.ASC,
                this.orderBy);
    }
}
