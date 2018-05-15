package com.iprzd.zshop.http.response;

public class PageResponse extends BaseResponse {
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
