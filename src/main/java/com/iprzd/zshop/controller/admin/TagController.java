package com.iprzd.zshop.controller.admin;

import com.iprzd.zshop.controller.admin.request.IdRequest;
import com.iprzd.zshop.controller.admin.request.ListRequest;
import com.iprzd.zshop.controller.admin.request.tag.TagRequest;
import com.iprzd.zshop.controller.admin.response.AdminResponse;
import com.iprzd.zshop.controller.admin.response.TagPageResponse;
import com.iprzd.zshop.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/tag")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/store")
    public AdminResponse store(@RequestBody TagRequest request) {
        return tagService.save(request);
    }

    @GetMapping("/list")
    public TagPageResponse all(@RequestParam int page,
                               @RequestParam int size,
                               @RequestParam String orderBy,
                               @RequestParam int order) {
        return this.tagService.findAll(new ListRequest(page, size, orderBy, order));
    }

    @PostMapping("/delete")
    public AdminResponse delete(@RequestBody IdRequest request) {
        return this.tagService.delete(request.getId());
    }
}
