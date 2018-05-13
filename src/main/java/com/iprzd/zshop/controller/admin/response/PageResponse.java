package com.iprzd.zshop.controller.admin.response;

public class PageResponse extends AdminResponse {
    private int total;
    private int pages;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
