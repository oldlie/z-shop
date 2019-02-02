package com.iprzd.zshop.controller.admin.article;

import com.iprzd.zshop.entity.article.Menu;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.article.ArticleMenuListResponse;
import com.iprzd.zshop.service.article.ArticleMenuService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/article/menu")
public class ArticleMenuController {

    private ArticleMenuService articleMenuService;

    public ArticleMenuController(ArticleMenuService articleMenuService) {
        this.articleMenuService = articleMenuService;
    }

    @PostMapping("/store")
    public BaseResponse store(@RequestBody Menu menu) {
        return this.articleMenuService.store(menu);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody IdRequest request) {
        return this.articleMenuService.delete(request.getId());
    }

    @PostMapping("/children")
    public ArticleMenuListResponse children(@RequestBody IdRequest request) {
        return this.articleMenuService.findAllByParentId(request.getId());
    }
}
