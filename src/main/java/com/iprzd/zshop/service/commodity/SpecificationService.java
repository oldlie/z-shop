package com.iprzd.zshop.service.commodity;

import com.iprzd.zshop.controller.admin.request.ListRequest;
import com.iprzd.zshop.controller.admin.request.commodity.SpecListByTitleRequest;
import com.iprzd.zshop.controller.admin.response.AdminResponse;
import com.iprzd.zshop.controller.admin.response.SpecPageResponse;
import com.iprzd.zshop.controller.response.StatusCode;
import com.iprzd.zshop.entity.commodity.Specification;
import com.iprzd.zshop.repository.commodity.SpecificationRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecificationService {

    private SpecificationRepository repository;

    public SpecificationService(SpecificationRepository specificationRepository) {
        this.repository = specificationRepository;
    }

    public AdminResponse store(Specification specification) {
        AdminResponse response = new AdminResponse();
        Specification tag;
        if (specification.getId() > 0) {
            tag = this.repository.findById(specification.getId()).get();
        } else {
            tag = new Specification();
        }
        tag.setTitle(specification.getTitle());
        tag.setCommodityId(specification.getCommodityId());
        tag.setBreed(specification.getBreed());
        tag.setOrigin(specification.getOrigin());
        tag.setFeature(specification.getFeature());
        tag.setSpec(specification.getSpec());
        tag.setStore(specification.getStore());
        tag.setProductDatetime(specification.getProductDatetime());
        tag.setPrice(specification.getPrice());
        tag.setInventory(specification.getInventory());
        this.repository.save(tag);
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));
        return response;
    }

    public AdminResponse delete(long id) {
        AdminResponse response = new AdminResponse();
        Optional<Specification> optional = this.repository.findById(id);
        if (!optional.isPresent()) {
            response.setStatus(StatusCode.DELETE_COMMODITY_SPEC_FAILED);
            response.setMessage("这个规格已经不存在了，请刷新前端页面试试。");
            return  response;
        }

        this.repository.delete(optional.get());
        return StatusCode.adminSuccessResponse(response);
    }

    public SpecPageResponse findAll(ListRequest request) {
        Page<Specification> page = this.repository.findAll(
                PageRequest.of(request.getPage(), request.getSize(), request.getSort()));
        return this.buildPageResponse(page);
    }

    public SpecPageResponse findAllByTitle(SpecListByTitleRequest request) {
        Page<Specification> page = this.repository.findAllByTitle(request.getTitle(),
                PageRequest.of(request.getPage(), request.getSize(), request.getSort()));
        return this.buildPageResponse(page);
    }

    private SpecPageResponse buildPageResponse(@NotNull Page<Specification> page) {
        SpecPageResponse response = new SpecPageResponse();
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.getMessage(0));
        response.setList(page.getContent());
        response.setPages(page.getTotalPages());
        response.setTotal(page.getNumber());
        return response;
    }
}
