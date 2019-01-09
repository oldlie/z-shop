package com.iprzd.zshop.service.commodity;

import com.iprzd.zshop.entity.Tag;
import com.iprzd.zshop.entity.UploadFile;
import com.iprzd.zshop.entity.commodity.*;
import com.iprzd.zshop.entity.home.HomeCommodity;
import com.iprzd.zshop.http.StatusCode;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.request.admin.commodity.CommodityRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.CommodityImageListResponse;
import com.iprzd.zshop.http.response.CommodityImageResponse;
import com.iprzd.zshop.http.response.CommodityInfoResponse;
import com.iprzd.zshop.http.response.admin.commodity.CommodityListResponseBase;
import com.iprzd.zshop.repository.TagRepository;
import com.iprzd.zshop.repository.UploadFileRepository;
import com.iprzd.zshop.repository.commodity.CommodityImageRepository;
import com.iprzd.zshop.repository.commodity.CommodityRepository;
import com.iprzd.zshop.repository.commodity.MenuRepository;
import com.iprzd.zshop.repository.commodity.SpecificationRepository;
import com.iprzd.zshop.repository.home.HomeCommodityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommodityService {

    private static Logger logger = LoggerFactory.getLogger(CommodityService.class);

    private CommodityRepository commodityRepository;
    private CommodityImageRepository commodityImageRepository;
    private HomeCommodityRepository homeCommodityRepository;
    private MenuRepository menuRepository;
    private SpecificationRepository specificationRepository;
    private TagRepository tagRepository;
    private UploadFileRepository uploadFileRepository;

    public CommodityService(CommodityRepository commodityRepository,
                            CommodityImageRepository commodityImageRepository,
                            HomeCommodityRepository homeCommodityRepository,
                            MenuRepository menuRepository,
                            SpecificationRepository specificationRepository,
                            TagRepository tagRepository,
                            UploadFileRepository uploadFileRepository){
        this.commodityRepository = commodityRepository;
        this.commodityImageRepository = commodityImageRepository;
        this.homeCommodityRepository = homeCommodityRepository;
        this.menuRepository = menuRepository;
        this.specificationRepository = specificationRepository;
        this.tagRepository = tagRepository;
        this.uploadFileRepository = uploadFileRepository;
    }

    @Transactional
    public BaseResponse store(CommodityRequest request) {
        BaseResponse response = new BaseResponse();

        Optional<Commodity> optional = null;
        Commodity commodity;

        if (request.getId() > 0) {
            optional = this.commodityRepository.findById(request.getId());
            if (optional.isPresent()) {
                commodity = optional.get();
            } else {
                response.setStatus(StatusCode.SAVE_COMMODITY_FAILED);
                response.setMessage("要修改的商品已经不存在了。");
                return response;
            }
        } else {
            commodity = new Commodity();
            commodity.setCreateAt(new Date());
        }

        commodity.setTitle(request.getTitle());
        commodity.setSummary(request.getSummary());
        commodity.setImage(request.getImage());
        commodity.setComment(request.getComment());
        commodity.setDescription(request.getDescription());
        commodity.setStatus(request.getStatus());
        commodity.setUpdateAt(new Date());

        try {
            String[] specArray = request.getSpecifications().split(",");
            List<Long> ids = new ArrayList<>();
            for (String spec : specArray) {
                ids.add(Long.parseLong(spec));
            }
            List<Specification> specificationList = this.specificationRepository.findAllByIdIn(ids);
            commodity.setSpecifications(specificationList);

            String[] menuArray = request.getMenus().split(",");
            ids = new ArrayList<>();
            for (String menuStr : menuArray) {
                ids.add(Long.parseLong(menuStr));
            }
            List<Menu> menuList = this.menuRepository.findAllByIdIn(ids);
            commodity.setMenus(menuList);

            String[] tagArray = request.getTags().split(",");
            ids = new ArrayList<>();
            for (String tagStr : tagArray) {
                ids.add(Long.parseLong(tagStr));
            }
            List<Tag> tagList = this.tagRepository.findAllByIdIn(ids);
            commodity.setTags(tagList);


        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            response.setStatus(StatusCode.SAVE_COMMODITY_FAILED);
            response.setMessage(e.getLocalizedMessage());
            return response;
        }

        commodity = this.commodityRepository.save(commodity);

        try {
            List<CommodityImage> existImages =
                    this.commodityImageRepository.findAllByCommodityIdOrderById(commodity.getId());
            if (existImages != null && existImages.size() > 0) {
                this.commodityImageRepository.deleteAll(existImages);
            }

            String[] imageArray =request.getImages().split(",");
            List<Long> ids = new ArrayList<>();
            for (String image : imageArray) {
                ids.add(Long.parseLong(image));
            }
            List<UploadFile> files = this.uploadFileRepository.findAllByIdIn(ids);
            List<CommodityImage> images = new ArrayList<>();
            for (UploadFile file : files) {
                CommodityImage image = new CommodityImage();
                image.setCommodityId(commodity.getId());
                image.setImagePath(file.getPath());
                images.add(image);
            }
            this.commodityImageRepository.saveAll(images);
        } catch (Exception e) {
            response.setStatus(StatusCode.SAVE_COMMODITY_FAILED);
            response.setMessage(e.getLocalizedMessage());
            return response;
        }

        return StatusCode.successResponse(response);
    }

    @Transactional
    public CommodityListResponseBase findAll(ListRequest request) {
        CommodityListResponseBase response = new CommodityListResponseBase();
        Page<Commodity> page = this.commodityRepository.findAll(
                PageRequest.of(request.getPage(), request.getSize(), request.getSort())
        );
        response.setStatus(StatusCode.SUCCESS);
        response.setList(page.getContent());
        response.setPages(page.getTotalPages());
        return response;
    }

    public CommodityInfoResponse findOneById(final Long id) {
        CommodityInfoResponse response = new CommodityInfoResponse();
        Optional<Commodity> optional = this.commodityRepository.findById(id);
        if (optional.isPresent()) {
            CommodityInfo info = new CommodityInfo();
            info.setCommodity(optional.get());
            List<CommodityImage> images = this.commodityImageRepository.findAllByCommodityIdOrderById(id);
            info.setImages(images);
            response.setCommodityInfo(info);
        } else {
            response.setStatus(1);
            response.setMessage("这件商品找不到了");
        }
        return response;
    }

    @Transactional
    public BaseResponse delete(IdRequest request) {
        BaseResponse response = new BaseResponse();
        Optional<Commodity> optional = this.commodityRepository.findById(request.getId());
        if (!optional.isPresent()) {
            response.setStatus(StatusCode.SUCCESS);
            return response;
        }
        Commodity commodity = optional.get();
        // 有点儿问题哦

        this.commodityRepository.delete(commodity);
        response.setStatus(StatusCode.SUCCESS);
        return response;
    }

    public BaseResponse add2Home(Long id) {
        HomeCommodity homeCommodity = this.homeCommodityRepository.findFirstByCommodityId(id);
        if (homeCommodity == null) {
            homeCommodity = new HomeCommodity();
            homeCommodity.setCommodityId(id);
            homeCommodity.setSequence(0);
            this.homeCommodityRepository.save(homeCommodity);
        }
        return new BaseResponse();
    }

    public CommodityImageListResponse listCommodityImage(Long commodityId) {
        CommodityImageListResponse response = new CommodityImageListResponse();
        List<CommodityImage> list = this.commodityImageRepository.findAllByCommodityIdOrderById(commodityId);
        response.setList(list);
        return response;
    }

    public CommodityImageResponse findCommodityImage(Long commodityId) {
        CommodityImageResponse response = new CommodityImageResponse();
        CommodityImage image = this.commodityImageRepository.findFirstByCommodityIdOrderByIdAsc(commodityId);
        response.setImage(image);
        return response;
    }
}
