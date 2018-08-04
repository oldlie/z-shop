import { Injectable } from '@angular/core';
import { Base } from '../response/response';
import { CoreService } from './core.service';
import {
  CommodityMenuListResponse,
  CommoditySpecListResponse,
  CommodityMenu,
  CommodityListResponse,
  CommodityImageListResponse
} from '../response/commodity.response';
import { CommoditySpecVI, CommodityVI } from '../pages/shop/commodity-vi';
import { Tag } from '../response/tag';
import { HomeCommodityListReponse } from '../response/home.response';

@Injectable()
export class CommodityService {

  constructor(private core: CoreService) { }

  // region Menu

  saveMenu(title: String, parentId: number, comment: String): Promise<Base> {
    const url = `${this.core.UrlPrefix}/admin/commodity/menu/store`;
    return this.core.post(url, {
      id: 0,
      title: title,
      parentId: parentId,
      children: 0,
      comment: comment
    }).toPromise().then(res => res as Base);
  }

  deleteMenu(id: number): Promise<Base> {
    const url = `${this.core.UrlPrefix}/admin/commodity/menu/delete`;
    return this.core.post(url, { id: id }).toPromise().then(res => res as Base);
  }

  findMenuByParentId(parentId: number): Promise<CommodityMenuListResponse> {
    const url = `${this.core.UrlPrefix}/admin/commodity/menu/children`;
    return this.core.get(url, { id: parentId }).toPromise().then(res => res as CommodityMenuListResponse);
  }

  // endregion

  // region Spec

  saveSpec(spec: CommoditySpecVI): Promise<Base> {
    const url = `${this.core.UrlPrefix}/admin/commodity/spec/store`;
    return this.core.post(url, spec).toPromise().then(res => res as Base);
  }

  listSpec(page: number, size: number): Promise<CommoditySpecListResponse> {
    const url = `${this.core.UrlPrefix}/admin/commodity/spec/list`;
    return this.core.get(url, { page: page, size: size, orderBy: 'id', order: 1 }).toPromise()
      .then(res => res as CommoditySpecListResponse);
  }

  deleteSpec(id: number): Promise<Base> {
    const url = `${this.core.UrlPrefix}/admin/commodity/spec/delete`;
    return this.core.post(url, { id: id }).toPromise().then(res => res as Base);
  }
  // endregion

  add2Home(id: number): Promise<Base> {
    const url = `${this.core.UrlPrefix}/admin/commodity/home`;
    return this.core.post(url, {id: id}).toPromise().then(x => x as Base);
  }

  save(model: CommodityVI,
    imageList: Array<string>,
    menuList: Array<CommodityMenu>,
    tagList: Array<Tag>,
    specList: Array<CommoditySpecVI>
  ): Promise<Base> {
    const url = `${this.core.UrlPrefix}/admin/commodity/store`;
    const menus = [];
    for (const item of menuList) {
      menus.push(item.id);
    }
    const tags = [];
    for (const item of tagList) {
      tags.push(item.id);
    }
    const specifications = [];
    for (const item of specList) {
      specifications.push(item.id);
    }
    const params = {
      id: model.id,
      title: model.title,
      summary: model.summary,
      comment: model.comment,
      image: model.image,
      description: model.description,
      status: model.status,
      menus: menus.join(','),
      tags: tags.join(','),
      specifications: specifications.join(','),
      images: imageList.join(',')
    };
    return this.core.post(url, params).toPromise().then(res => res as Base);
  }

  list(page: number, size: number, orderBy: string, order: number): Promise<CommodityListResponse> {
    const url = `${this.core.UrlPrefix}/admin/commodity/list`;
    const params = {
      page: page.toString(),
      size: size.toString(),
      orderBy: orderBy,
      order: order.toString()
    };

    return this.core.get(url, params).toPromise().then(res => res as CommodityListResponse);
  }

  delete(id: number): Promise<Base> {
    const url = `${this.core.UrlPrefix}/admin/commodity/delete`;
    return this.core.post(url, { id: id }).toPromise().then(res => res as Base);
  }

  listImages(id: number): Promise<CommodityImageListResponse> {
    const url = `${this.core.UrlPrefix}/admin/commodity/images`;
    return this.core.post(url, { id: id }).toPromise().then(x => x as CommodityImageListResponse);
  }
}
