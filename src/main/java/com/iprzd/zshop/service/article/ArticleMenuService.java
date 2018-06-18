package com.iprzd.zshop.service.article;

import com.iprzd.zshop.entity.article.Menu;
import com.iprzd.zshop.http.StatusCode;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.article.ArticleMenuListResponse;
import com.iprzd.zshop.repository.ArticleMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleMenuService {

    private ArticleMenuRepository menuRepository;

    public ArticleMenuService(ArticleMenuRepository articleMenuRepository) {
        this.menuRepository = articleMenuRepository;
    }

    @Transactional
    public BaseResponse store(Menu menu) {
        BaseResponse response = new BaseResponse();
        if (menu.getTitle().equals("")) {
            response.setStatus(StatusCode.SAVE_ARTICLE_FAILED);
            response.setMessage("请填写栏目名称");
            return response;
        }

        Optional<Menu> optionalMenu = this.menuRepository.findById(menu.getParentId());
        menu = this.menuRepository.save(menu);
        /*
         * 不是更新，有父节点，且父节点在数据库中存在
         */
        if (menu.getId() > 0 && menu.getParentId() > 0 && optionalMenu.isPresent()) {
            Menu parent =optionalMenu.get();
            parent.setChildren(parent.getChildren() + 1);
            this.menuRepository.save(parent);
        }



        return response;
    }

    @Transactional
    public BaseResponse delete(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<Menu> optionalMenu = this.menuRepository.findById(id);
        if (optionalMenu.isPresent()) {
            Menu menu =optionalMenu.get();
            if (menu.getParentId() > 0) {
                optionalMenu = this.menuRepository.findById(menu.getParentId());
                if (optionalMenu.isPresent()) {
                    Menu parent = optionalMenu.get();
                    int children = parent.getChildren();
                    children = children > 1 ? children - 1 : 0;
                    parent.setChildren(children);
                    this.menuRepository.save(parent);
                }
            }
            this.menuRepository.delete(menu);
        }
        return response;
    }

    public ArticleMenuListResponse findAllByParentId(Long parentId) {
        ArticleMenuListResponse response = new ArticleMenuListResponse();
        List<Menu> list =this.menuRepository.findByParentId(parentId);
        response.setList(list);
        return response;
    }
}
