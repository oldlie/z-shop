package com.iprzd.zshop.service;

import com.iprzd.zshop.controller.response.BaseResponse;
import com.iprzd.zshop.controller.response.CommodityMenuListResponse;
import com.iprzd.zshop.controller.response.StatusCode;
import com.iprzd.zshop.entity.commodity.Menu;
import com.iprzd.zshop.repository.commodity.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommodityMenuService {

    private MenuRepository menuRepository;

    public CommodityMenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public BaseResponse store(Menu commodityMenu) {
        BaseResponse response = new BaseResponse();
        commodityMenu = this.menuRepository.save(commodityMenu);
        if (commodityMenu.getId() > 0) {
            response.setStatus(StatusCode.SUCCESS);
            response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));
        } else {
            response.setStatus(StatusCode.SAVE_COMMODITY_MENU_FAILED);
            response.setMessage(StatusCode.getMessage(StatusCode.SAVE_COMMODITY_MENU_FAILED));
        }
        return response;
    }

    public BaseResponse delete(long id) {
        BaseResponse response = new BaseResponse();
        this.menuRepository.deleteById(id);
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));
        return response;
    }

    public BaseResponse update(long id, String title, long parentId, String comment) {
        BaseResponse response = new BaseResponse();

        try {
            Menu commodityMenu = this.menuRepository.findById(id).get();
            commodityMenu.setTitle(title);
            commodityMenu.setParentId(parentId);
            commodityMenu.setComment(comment);
            this.menuRepository.save(commodityMenu);

            response.setStatus(StatusCode.SUCCESS);
            response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));

        } catch (NoSuchElementException e) {
            response.setStatus(StatusCode.CANNOT_FIND_COMMODITY_MENU_EXCEPTION);
            response.setMessage(StatusCode.getMessage(StatusCode.CANNOT_FIND_COMMODITY_MENU_EXCEPTION));
        }

        return response;
    }

    public CommodityMenuListResponse findByParentId(long parentId) {
        CommodityMenuListResponse response = new CommodityMenuListResponse();
        response.setList(this.menuRepository.findByParentId(parentId));
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));
        return response;
    }
}
