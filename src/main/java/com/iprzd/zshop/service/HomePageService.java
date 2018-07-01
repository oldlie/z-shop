package com.iprzd.zshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.entity.commodity.CommodityImage;
import com.iprzd.zshop.entity.commodity.CommodityInfo;
import com.iprzd.zshop.entity.home.Carousel;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.CarouselListResponse;
import com.iprzd.zshop.http.response.HomeCommodityListResponse;
import com.iprzd.zshop.repository.commodity.CommodityImageRepository;
import com.iprzd.zshop.repository.commodity.CommodityRepository;
import com.iprzd.zshop.repository.home.HomeCarouselRepository;

import com.iprzd.zshop.repository.home.HomeCommodityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HomePageService {

    private CommodityImageRepository commodityImageRepository;
    private CommodityRepository commodityRepository;
    private HomeCarouselRepository homeCarouselRepository;
    private HomeCommodityRepository homeCommodityRepository;

    public HomePageService(CommodityImageRepository commodityImageRepository,
                           CommodityRepository commodityRepository,
                           HomeCarouselRepository homeCarouselRepository,
                           HomeCommodityRepository homeCommodityRepository) {
        this.commodityImageRepository = commodityImageRepository;
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
        List<CommodityInfo> list = new ArrayList<>();
        List<Commodity> commodities = this.commodityRepository.findAllHomePageCommodities();
        for (Commodity commodity : commodities) {
            List<CommodityImage> images =
                    this.commodityImageRepository.findAllByCommodityIdOrderById(commodity.getId());
            CommodityInfo info = new CommodityInfo();
            info.setCommodity(commodity);
            info.setImages(images);
            list.add(info);
        }
        response.setList(list);
        return response;
    }

    @Transactional
    public BaseResponse deleteCommodity(Long id) {
        this.homeCommodityRepository.deleteByCommodityId(id);
        return new BaseResponse();
    }
    // endregion
}