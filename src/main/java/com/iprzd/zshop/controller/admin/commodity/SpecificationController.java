package com.iprzd.zshop.controller.admin.commodity;

import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.request.admin.commodity.SpecListByTitleRequest;
import com.iprzd.zshop.http.request.admin.commodity.SpecificationRequest;
import com.iprzd.zshop.http.response.admin.SpecBasePageResponse;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.entity.commodity.Specification;
import com.iprzd.zshop.service.commodity.SpecificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/commodity/spec")
public class SpecificationController {

    private SpecificationService specificationService;

    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @PostMapping("/store")
    public BaseResponse store(@RequestBody SpecificationRequest request) {
        return this.specificationService.store(request);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody IdRequest request) {
        return this.specificationService.delete(request.getId());
    }

    @GetMapping("/list")
    public SpecBasePageResponse findAll(@RequestParam int page,
                                        @RequestParam int size,
                                        @RequestParam String orderBy,
                                        @RequestParam int order) {
        return this.specificationService.findAll(new ListRequest(page, size, orderBy, order));
    }

    @GetMapping("/search")
    public SpecBasePageResponse findAllByTitle(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String orderBy,
            @RequestParam int order,
            @RequestParam String title
    ) {
        return this.specificationService.findAllByTitle(new SpecListByTitleRequest(page, size, orderBy, order, title));
    }
}
