package com.iprzd.zshop.controller.admin;

import com.iprzd.zshop.entity.home.Carousel;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.CarouselListResponse;
import com.iprzd.zshop.service.HomePageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/home-setting")
public class HomeSettingController {

    private HomePageService homePageService;

    public HomeSettingController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

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
        return this.homePageService.list();
    }
}
