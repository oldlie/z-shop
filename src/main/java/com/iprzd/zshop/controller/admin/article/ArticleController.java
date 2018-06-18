package com.iprzd.zshop.controller.admin.article;

import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.article.ArticleListResponse;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.request.admin.ArticleRequest;
import com.iprzd.zshop.service.article.ArticleService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody IdRequest request) {
        return this.articleService.delete(request);
    }

    @GetMapping("/list")
    public ArticleListResponse list(@RequestParam int page, @RequestParam int size, @RequestParam String orderBy,
            @RequestParam int order) {
        return this.articleService.findAll(new ListRequest(page, size, orderBy, order));
    }
}
