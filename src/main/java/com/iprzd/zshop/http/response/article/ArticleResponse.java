package com.iprzd.zshop.http.response.article;

import com.iprzd.zshop.entity.article.Article;
import com.iprzd.zshop.http.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleResponse extends BaseResponse {
    private Article article;
}
