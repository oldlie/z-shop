package com.iprzd.zshop.service.commodity;

import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.request.admin.commodity.SpecListByTitleRequest;
import com.iprzd.zshop.http.request.admin.commodity.SpecificationRequest;
import com.iprzd.zshop.http.response.admin.SpecBasePageResponse;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.StatusCode;
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

    public BaseResponse store(SpecificationRequest request) {
        BaseResponse response = new BaseResponse();

        Specification specification;
        if (request.getId() > 0) {
            Optional<Specification> optional = this.repository.findById(request.getId());
            if (optional.isPresent()) {
                specification = optional.get();
            } else {
                specification = new Specification();
            }
        } else {
            specification = new Specification();
        }

        specification.setId(request.getId());
        specification.setTitle(request.getTitle());
        specification.setCommodityId(request.getCommodityId());
        specification.setBreed(request.getBreed());
        specification.setOrigin(request.getOrigin());
        specification.setFeature(request.getFeature());
        specification.setSpec(request.getSpec());
        specification.setStore(request.getStore());
        specification.setProductDatetime(request.getProductDatetime());
        specification.setPrice(request.getPrice());
        specification.setInventory(request.getInventory());
        specification.setTypes(request.getTypes());

        this.repository.save(specification);
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));
        return response;
    }

    public BaseResponse delete(long id) {
        BaseResponse response = new BaseResponse();
        Optional<Specification> optional = this.repository.findById(id);
        if (!optional.isPresent()) {
            response.setStatus(StatusCode.DELETE_COMMODITY_SPEC_FAILED);
            response.setMessage("这个规格已经不存在了，请刷新前端页面试试。");
            return  response;
        }

        this.repository.delete(optional.get());
        return StatusCode.successResponse(response);
    }

    public SpecBasePageResponse findAll(ListRequest request) {
        Page<Specification> page = this.repository.findAll(
                PageRequest.of(request.getPage(), request.getSize(), request.getSort()));
        return this.buildPageResponse(page);
    }

    public SpecBasePageResponse findAllByTitle(SpecListByTitleRequest request) {
        Page<Specification> page = this.repository.findAllByTitle(request.getTitle(),
                PageRequest.of(request.getPage(), request.getSize(), request.getSort()));
        return this.buildPageResponse(page);
    }

    private SpecBasePageResponse buildPageResponse(@NotNull Page<Specification> page) {
        SpecBasePageResponse response = new SpecBasePageResponse();
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.getMessage(0));
        response.setList(page.getContent());
        response.setPages(page.getTotalPages());
        response.setTotal(page.getNumber());
        return response;
    }
}
