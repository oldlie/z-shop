package com.iprzd.zshop.controller.front;

import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.response.CarouselListResponse;
import com.iprzd.zshop.http.response.HomeCommodityListResponse;
import com.iprzd.zshop.http.response.article.ArticleListResponse;
import com.iprzd.zshop.service.HomePageService;
import com.iprzd.zshop.service.article.ArticleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    private ArticleService articleService;
    private HomePageService homePageService;

    public HomeController(ArticleService articleService,
                          HomePageService homePageService) {
        this.articleService = articleService;
        this.homePageService = homePageService;
    }

    @GetMapping("/carousel")
    public CarouselListResponse listCarousel() {
        return this.homePageService.listCarousel();
    }

    @GetMapping("/commodity")
    public HomeCommodityListResponse listCommodity() {
        return this.homePageService.listCommodity();
    }

    @GetMapping("/article")
    public ArticleListResponse listArticle(@RequestParam int page, @RequestParam int size, @RequestParam String orderBy,
                                           @RequestParam int order) {
        return this.articleService.findAll(new ListRequest(page, size, orderBy, order));
    }

    @GetMapping("/notify")
    public ArticleListResponse listHomeArticle() {
        return this.homePageService.listArticle();
    }
}
