package com.iprzd.zshop.controller.front;

import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.response.admin.TagPageResponse;
import com.iprzd.zshop.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/front/tag")
public class FrontTagController {

    private TagService tagService;

    public FrontTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public TagPageResponse list(@RequestParam int page,
                                @RequestParam int size,
                                @RequestParam String orderBy,
                                @RequestParam int order) {
        return this.tagService.findAll(new ListRequest(page, size, orderBy, order));
    }
}
