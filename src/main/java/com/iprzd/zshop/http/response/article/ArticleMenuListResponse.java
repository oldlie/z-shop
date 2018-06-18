package com.iprzd.zshop.http.response.article;

import com.iprzd.zshop.entity.article.Menu;
import com.iprzd.zshop.http.response.BaseResponse;

import java.util.List;

public class ArticleMenuListResponse extends BaseResponse {
    private List<Menu> list;

    public List<Menu> getList() {
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }
}
