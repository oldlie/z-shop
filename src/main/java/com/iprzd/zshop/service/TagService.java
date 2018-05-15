package com.iprzd.zshop.service;

import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.request.admin.TagRequest;
import com.iprzd.zshop.http.response.admin.TagPageResponse;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.StatusCode;
import com.iprzd.zshop.entity.Tag;
import com.iprzd.zshop.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public BaseResponse save(TagRequest request) {
        BaseResponse response = new BaseResponse();
        if (isTitleEmpty(request)) {
            response.setStatus(StatusCode.SAVE_TAG_FAILED);
            response.setMessage("请设置标签名称。");
            return response;
        }
        Tag tag;
        if (request.getId() > 0) {
            tag = this.tagRepository.findById(request.getId()).get();
        } else {
            tag = new Tag();
        }
        tag.setTitle(request.getTitle());
        tag.setParentId(request.getParentId());
        tag = this.tagRepository.save(tag);
        if (tag.getId() > 0) {
            response.setStatus(StatusCode.SUCCESS);
            response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));
        } else {
            response.setStatus(StatusCode.SAVE_TAG_FAILED);
            response.setMessage(tag.toString());
        }
        return response;
    }

    public BaseResponse delete(long id) {
        BaseResponse response = new BaseResponse();
        this.tagRepository.deleteById(id);
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));
        return response;
    }

    public TagPageResponse findAll(ListRequest request) {
        TagPageResponse response = new TagPageResponse();
        Page<Tag> page = this.tagRepository.findAll(
                PageRequest.of(request.getPage(), request.getSize(), request.getSort()));
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));
        response.setPages(page.getTotalPages());
        response.setTagList(page.getContent());
        response.setTotal(page.getNumber());
        return response;
    }

    @org.jetbrains.annotations.Contract("null -> true")
    private boolean isTitleEmpty(TagRequest request) {
        return request == null || request.getTitle() == null || "".equals(request.getTitle());
    }
}
