package com.iprzd.zshop.controller.front;

import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.response.CountResponse;
import com.iprzd.zshop.http.response.article.ArticleResponse;
import com.iprzd.zshop.service.article.ArticleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class FrontArticleController {

    private ArticleService articleService;

    public FrontArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/detail")
    public ArticleResponse article(@RequestParam long articleId) {
        return this.articleService.findById(articleId);
    }

    @PostMapping("/agree")
    public CountResponse agree(@RequestBody IdRequest request) {
        return this.articleService.agree(request.getId());
    }
}
