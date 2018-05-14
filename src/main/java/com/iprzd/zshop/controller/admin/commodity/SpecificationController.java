package com.iprzd.zshop.controller.admin.commodity;

import com.iprzd.zshop.controller.admin.request.IdRequest;
import com.iprzd.zshop.controller.admin.request.ListRequest;
import com.iprzd.zshop.controller.admin.request.commodity.SpecListByTitleRequest;
import com.iprzd.zshop.controller.admin.request.commodity.SpecificationRequest;
import com.iprzd.zshop.controller.admin.response.AdminResponse;
import com.iprzd.zshop.controller.admin.response.SpecPageResponse;
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
    public AdminResponse store(@RequestBody SpecificationRequest request) {
        Specification specification = new Specification();
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
        return this.specificationService.store(specification);
    }

    @PostMapping("/delete")
    public AdminResponse delete(@RequestBody IdRequest request) {
        return this.specificationService.delete(request.getId());
    }

    @GetMapping("/list")
    public SpecPageResponse findAll(@RequestParam int page,
                                @RequestParam int size,
                                @RequestParam String orderBy,
                                @RequestParam int order) {
        return this.specificationService.findAll(new ListRequest(page, size, orderBy, order));
    }

    @GetMapping("/search")
    public SpecPageResponse findAllByTitle(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String orderBy,
            @RequestParam int order,
            @RequestParam String title
    ) {
        return this.specificationService.findAllByTitle(new SpecListByTitleRequest(page, size, orderBy, order, title));
    }
}
