package com.iprzd.zshop.controller.admin.article;

import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.admin.ArticleRequest;
import com.iprzd.zshop.service.ArticleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/article")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/store")
    public BaseResponse store(@RequestBody ArticleRequest request) {
        return this.articleService.store(request);
    }
}
