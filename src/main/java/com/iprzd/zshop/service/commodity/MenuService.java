package com.iprzd.zshop.service.commodity;

import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.http.response.admin.commodity.MenuListResponse;
import com.iprzd.zshop.http.StatusCode;
import com.iprzd.zshop.entity.commodity.Menu;
import com.iprzd.zshop.repository.commodity.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MenuService {

    private MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public BaseResponse store(Menu menu) {
        BaseResponse response = new BaseResponse();

        if ("".equals(menu.getTitle())) {
            response.setStatus(StatusCode.SAVE_COMMODITY_MENU_FAILED);
            response.setMessage("请填写栏目名称。");
            return response;
        }

        Optional<Menu> optional;

        Menu commodityMenu;
        if (menu.getId() > 0) {
            optional = this.menuRepository.findById(menu.getId());
            if (optional.isPresent()) {
                commodityMenu = optional.get();
                commodityMenu.setTitle(menu.getTitle());
                commodityMenu.setParentId(menu.getParentId());
                commodityMenu.setComment(menu.getComment());
            } else {
                response.setStatus(StatusCode.SAVE_COMMODITY_MENU_FAILED);
                response.setMessage("这个栏目已经被其他用户删除，您可以重新添加这个栏目。");
                return response;
            }
        } else {
            commodityMenu = menu;
        }

        menu = this.menuRepository.save(commodityMenu);
        if (menu.getId() > 0) {
            if (menu.getParentId() > 0) {
                optional = this.menuRepository.findById(menu.getParentId());
                if (optional.isPresent()) {
                    Menu parentMenu = optional.get();
                    parentMenu.setChildren(parentMenu.getChildren() + 1);
                    this.menuRepository.save(parentMenu);
                }
            }

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

        if (!this.menuRepository.findById(id).isPresent()) {
            response.setStatus(StatusCode.DELETE_COMMODITY_MENU_FAILED);
            response.setMessage("该栏目已经不存在了，请刷新前端页面。");
            return response;
        }

        Menu menu = this.menuRepository.findById(id).get();
        if (menu.getChildren() > 0) {
            response.setStatus(StatusCode.DELETE_COMMODITY_MENU_FAILED);
            response.setMessage("含有下级栏目，还不能删除。");
            return response;
        }

        Optional<Menu> optional = this.menuRepository.findById(menu.getParentId());
        if (optional.isPresent()) {
            // 有父节点的话，更新父节点计数
            Menu parent = optional.get();
            int children = parent.getChildren() - 1;
            parent.setChildren(children <= 0 ? 0 : children);
            this.menuRepository.save(parent);
        }

        this.menuRepository.delete(menu);
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

    public MenuListResponse findByParentId(long parentId) {
        MenuListResponse response = new MenuListResponse();
        response.setList(this.menuRepository.findByParentId(parentId));
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.getMessage(StatusCode.SUCCESS));
        return response;
    }
}
