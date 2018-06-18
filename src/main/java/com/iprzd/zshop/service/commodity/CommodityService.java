package com.iprzd.zshop.service.commodity;

import com.iprzd.zshop.entity.Tag;
import com.iprzd.zshop.entity.commodity.Commodity;
import com.iprzd.zshop.entity.commodity.Menu;
import com.iprzd.zshop.entity.commodity.Specification;
import com.iprzd.zshop.http.StatusCode;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.request.admin.commodity.CommodityRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.admin.commodity.CommodityListResponse;
import com.iprzd.zshop.repository.TagRepository;
import com.iprzd.zshop.repository.commodity.CommodityRepository;
import com.iprzd.zshop.repository.commodity.MenuRepository;
import com.iprzd.zshop.repository.commodity.SpecificationRepository;
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
    private MenuRepository menuRepository;
    private SpecificationRepository specificationRepository;
    private TagRepository tagRepository;

    public CommodityService(CommodityRepository commodityRepository,
                            MenuRepository menuRepository,
                            SpecificationRepository specificationRepository,
                            TagRepository tagRepository){
        this.commodityRepository = commodityRepository;
        this.menuRepository = menuRepository;
        this.specificationRepository = specificationRepository;
        this.tagRepository = tagRepository;
    }

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


        this.commodityRepository.save(commodity);
        return StatusCode.successResponse(response);
    }

    public CommodityListResponse findAll(ListRequest request) {
        CommodityListResponse response = new CommodityListResponse();
        Page<Commodity> page = this.commodityRepository.findAll(
                PageRequest.of(request.getPage(), request.getSize(), request.getSort())
        );
        response.setStatus(StatusCode.SUCCESS);
        response.setList(page.getContent());
        response.setPages(page.getTotalPages());
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
}
