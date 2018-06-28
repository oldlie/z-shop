package com.iprzd.zshop.service;

import java.util.List;
import java.util.Optional;

import com.iprzd.zshop.entity.home.Carousel;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.CarouselListResponse;
import com.iprzd.zshop.repository.home.HomeCarouselRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HomePageService {

    private HomeCarouselRepository homeCarouselRepository;

    public HomePageService(HomeCarouselRepository homeCarouselRepository) {
        this.homeCarouselRepository = homeCarouselRepository;
    }

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

    public CarouselListResponse list() {
        CarouselListResponse response = new CarouselListResponse();
        List<Carousel> list =
                this.homeCarouselRepository.findAll(Sort.by(Sort.Direction.ASC, "sequence"));
        response.setList(list);
        return response;
    }
}