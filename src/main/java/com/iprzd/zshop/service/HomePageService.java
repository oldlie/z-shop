package com.iprzd.zshop.service;

import java.util.List;
import java.util.Optional;

import com.iprzd.zshop.entity.article.Article;
import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.entity.home.Carousel;
import com.iprzd.zshop.entity.home.HomeArticle;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.CarouselListResponse;
import com.iprzd.zshop.http.response.HomeCommodityListResponse;
import com.iprzd.zshop.http.response.article.ArticleListResponseBase;
import com.iprzd.zshop.repository.ArticleRepository;
import com.iprzd.zshop.repository.commodity.CommodityImageRepository;
import com.iprzd.zshop.repository.commodity.CommodityRepository;
import com.iprzd.zshop.repository.home.HomeArticleRepository;
import com.iprzd.zshop.repository.home.HomeCarouselRepository;

import com.iprzd.zshop.repository.home.HomeCommodityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HomePageService {

    private ArticleRepository articleRepository;
    private CommodityRepository commodityRepository;
    private HomeArticleRepository homeArticleRepository;
    private HomeCarouselRepository homeCarouselRepository;
    private HomeCommodityRepository homeCommodityRepository;

    public HomePageService(ArticleRepository articleRepository,
                           CommodityImageRepository commodityImageRepository,
                           CommodityRepository commodityRepository,
                           HomeArticleRepository homeArticleRepository,
                           HomeCarouselRepository homeCarouselRepository,
                           HomeCommodityRepository homeCommodityRepository) {
        this.articleRepository = articleRepository;
        this.commodityRepository = commodityRepository;
        this.homeArticleRepository = homeArticleRepository;
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
        List<Commodity> commodities = this.commodityRepository.findAllHomePageCommodities();
        response.setList(commodities);
        return response;
    }

    @Transactional
    public BaseResponse deleteCommodity(Long id) {
        this.homeCommodityRepository.deleteByCommodityId(id);
        return new BaseResponse();
    }
    // endregion

    // region article
    public ArticleListResponseBase listArticle() {
        ArticleListResponseBase response = new ArticleListResponseBase();
        List<Article> list = this.articleRepository.findAllHomeArticles();
        response.setList(list);
        return response;
    }

    public BaseResponse deleteArticle(final Long id) {
        BaseResponse response = new BaseResponse();
        HomeArticle homeArticle = this.homeArticleRepository.findFirstByArticleId(id);
        if (homeArticle != null) {
            this.homeArticleRepository.delete(homeArticle);
        }
        return response;
    }
    // endregion
}