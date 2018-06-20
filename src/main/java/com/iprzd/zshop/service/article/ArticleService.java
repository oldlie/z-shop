package com.iprzd.zshop.service.article;

import com.iprzd.zshop.core.verify.LengthVerifier;
import com.iprzd.zshop.core.verify.NotEmptyVerifier;
import com.iprzd.zshop.core.verify.Verify;
import com.iprzd.zshop.entity.Tag;
import com.iprzd.zshop.entity.article.Article;
import com.iprzd.zshop.entity.article.Menu;
import com.iprzd.zshop.http.StatusCode;
import com.iprzd.zshop.http.request.IdRequest;
import com.iprzd.zshop.http.request.ListRequest;
import com.iprzd.zshop.http.response.article.ArticleListResponse;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.request.admin.ArticleRequest;
import com.iprzd.zshop.repository.ArticleMenuRepository;
import com.iprzd.zshop.repository.ArticleRepository;
import com.iprzd.zshop.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private ArticleMenuRepository articleMenuRepository;
    private TagRepository tagRepository;


    public ArticleService(ArticleRepository articleRepository,
                          ArticleMenuRepository articleMenuRepository,
                          TagRepository tagRepository) {
        this.articleRepository = articleRepository;
        this.articleMenuRepository = articleMenuRepository;
        this.tagRepository = tagRepository;
    }

    // region Article
    public BaseResponse store(ArticleRequest request) {
        BaseResponse response = new BaseResponse();
        Verify verify = new Verify(new LengthVerifier(request.getTitle(), 1, 32,
                "标题应在32字符以内"))
                .add(new LengthVerifier(request.getSummary(), 1, 255,
                        "摘要应在32字符以内"))
                .add(new LengthVerifier(request.getAuthor(), 1, 32,
                        "作者名字应在32字符以内"))
                .add(new NotEmptyVerifier(request.getMenus(), "需要选择栏目"));
        if (verify.verifyAll()) {
            Article article;
            Optional<Article> optional = this.articleRepository.findById(request.getId());
            if (request.getId() > 0 && optional.isPresent()) {
                article = optional.get();
            } else {
                article = new Article();
                article.setCreateAt(new Date());
                article.setPublishAt(new Date());
            }

            article.setTitle(request.getTitle());
            article.setSummary(request.getSummary());
            article.setContent(request.getContent());
            String url = request.getImageUrl();
            url = url == null || "".equals(url) ? "images/article/default.png" : url;
            article.setImageUrl(url);
            article.setAuthor(request.getAuthor());
            article.setAuthorId(request.getAuthorId());
            article.setUpdateAt(new Date());

            if (null != request.getTags() && !"".equals(request.getTags())) {
                String[] tags = request.getTags().split(",");
                if (tags.length > 0) {
                    List<Long> tagIdList = new ArrayList<>();
                    for (String tag : tags) {
                        tagIdList.add(Long.parseLong(tag));
                    }
                    List<Tag> tagList = this.tagRepository.findAllByIdIn(tagIdList);
                    article.setTags(tagList);
                }
            }

            String[] menuIds = request.getMenus().split(",");
            List<Long> menuIdList = new ArrayList<>();
            for (String id : menuIds) {
                menuIdList.add(Long.parseLong(id));
            }
            List<Menu> menuList = this.articleMenuRepository.findAllByIdIn(menuIdList);
            article.setMenus(menuList);
            this.articleRepository.save(article);

            response.setStatus(StatusCode.SUCCESS);
        } else {
            response.setStatus(StatusCode.SAVE_ARTICLE_FAILED);
            response.setMessage(response.getMessage());
        }
        return response;
    }

    public BaseResponse delete(IdRequest request) {
        BaseResponse response = new BaseResponse();
        this.articleRepository.deleteById(request.getId());
        return response;
    }

    public ArticleListResponse findAll(ListRequest request) {
        ArticleListResponse response = new ArticleListResponse();
        Page<Article> page = this.articleRepository.findAll(
                PageRequest.of(request.getPage(), request.getSize(), request.getSort())
        );
        response.setList(page.getContent());
        response.setPages(page.getTotalPages());
        return response;
    }
    // endregion
}
