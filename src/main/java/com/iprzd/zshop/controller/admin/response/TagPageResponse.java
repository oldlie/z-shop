package com.iprzd.zshop.controller.admin.response;

import com.iprzd.zshop.entity.Tag;

import java.util.List;

public class TagPageResponse extends AdminResponse {
    private int total;
    private int pages;
    private List<Tag> tagList;

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

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
