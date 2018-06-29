package com.iprzd.zshop.service;

import java.util.List;
import java.util.Optional;

import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.entity.home.Carousel;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.CarouselListResponse;
import com.iprzd.zshop.http.response.HomeCommodityListResponse;
import com.iprzd.zshop.repository.commodity.CommodityRepository;
import com.iprzd.zshop.repository.home.HomeCarouselRepository;

import com.iprzd.zshop.repository.home.HomeCommodityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HomePageService {

    private CommodityRepository commodityRepository;
    private HomeCarouselRepository homeCarouselRepository;
    private HomeCommodityRepository homeCommodityRepository;

    public HomePageService(CommodityRepository commodityRepository,
                           HomeCarouselRepository homeCarouselRepository,
                           HomeCommodityRepository homeCommodityRepository) {
        this.commodityRepository = commodityRepository;
        this.homeCarouselRepository = homeCarouselRepository;
        this.homeCommodityRepository = homeCommodityRepository;
    }

    // region Carousel

    public BaseResponse storeCarousel(Carousel carousel) {
        BaseResponse response = new BaseResponse();
        this.homeCarouselRepository.save(carousel);
        return response;
    }

    public BaseResponse deleteCarousel(Long id) {
        BaseResponse response = new BaseResponse();
        this.homeCarouselRepository.deleteById(id);
        Optional<Carousel> optional = this.homeCarouselRepository.findById(id);
        if (optional.isPresent()) {
            this.homeCarouselRepository.delete(optional.get());
        }
        return response;
    }

    public CarouselListResponse listCarousel() {
        CarouselListResponse response = new CarouselListResponse();
        List<Carousel> list =
                this.homeCarouselRepository.findAll(Sort.by(Sort.Direction.ASC, "sequence"));
        response.setList(list);
        return response;
    }

    // endregion

    // region commodity
    public HomeCommodityListResponse listCommodity() {
        HomeCommodityListResponse response = new HomeCommodityListResponse();
        List<Commodity> list = this.commodityRepository.findAllHomePageCommodities();
        response.setList(list);
        return response;
    }

    public BaseResponse deleteCommodity(Long id) {
        this.homeCommodityRepository.deleteById(id);
        return new BaseResponse();
    }
    // endregion
}