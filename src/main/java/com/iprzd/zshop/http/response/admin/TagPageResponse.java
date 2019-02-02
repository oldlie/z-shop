package com.iprzd.zshop.http.response.admin;

import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.entity.Tag;

import java.util.List;

public class TagPageResponse extends BaseResponse {
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
