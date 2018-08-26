package com.iprzd.zshop.http.response.article;

import com.iprzd.zshop.entity.article.Article;
import com.iprzd.zshop.http.response.BasePageResponse;

import java.util.List;

public class ArticleListResponseBase extends BasePageResponse {
    private List<Article> list;

    public List<Article> getList() {
        return list;
    }

    public void setList(List<Article> list) {
        this.list = list;
    }
}
