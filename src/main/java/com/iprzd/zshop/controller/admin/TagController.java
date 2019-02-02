package com.iprzd.zshop.controller.admin;

import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.request.admin.TagRequest;
import com.iprzd.zshop.http.response.admin.TagPageResponse;
import com.iprzd.zshop.http.response.BaseResponse;
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
    public BaseResponse store(@RequestBody TagRequest request) {
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
    public BaseResponse delete(@RequestBody IdRequest request) {
        return this.tagService.delete(request.getId());
    }
}
