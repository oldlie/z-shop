package com.iprzd.zshop.controller.admin;

import com.iprzd.zshop.entity.home.Carousel;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.CarouselListResponse;
import com.iprzd.zshop.http.response.HomeCommodityListResponse;
import com.iprzd.zshop.http.response.article.ArticleListResponseBase;
import com.iprzd.zshop.service.HomePageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/home-setting")
public class HomeSettingController {

    private HomePageService homePageService;

    public HomeSettingController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    // region carousel

    @PostMapping("/carousel/add")
    public BaseResponse addCarousel(@RequestBody Carousel carousel) {
        return this.homePageService.storeCarousel(carousel);
    }

    @PostMapping("/carousel/delete")
    public BaseResponse deleteCarousel(@RequestBody IdRequest request) {
        return this.homePageService.deleteCarousel(request.getId());
    }

    @GetMapping("/carousel/list")
    public CarouselListResponse listCarousel() {
        return this.homePageService.listCarousel();
    }

    // endregion

    // region commodity
    @GetMapping("/commodity/list")
    public HomeCommodityListResponse listCommodity() {
        return this.homePageService.listCommodity();
    }

    @PostMapping("/commodity/delete")
    public BaseResponse deleteCommodity(@RequestBody IdRequest request) {
        return this.homePageService.deleteCommodity(request.getId());
    }
    // endregion

    @GetMapping("/article/list")
    public ArticleListResponseBase listArticle() {
        return this.homePageService.listArticle();
    }

    @PostMapping("/article/delete")
    public BaseResponse deleteArticle(@RequestBody IdRequest request) {
        return this.homePageService.deleteArticle(request.getId());
    }
}
