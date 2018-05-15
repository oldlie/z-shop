import { Injectable } from '@angular/core';
import { Base } from '../response/response';
import { CoreService } from './core.service';
import { CommodityMenuListResponse, CommoditySpecListResponse } from '../response/commodity';
import { CommoditySpecVI } from '../pages/shop/commodity-vi';

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

  saveSpec(spec: CommoditySpecVI) {
    const url = `${this.core.UrlPrefix}/admin/commodity/spec/store`;
    return this.core.post(url, spec).toPromise().then(res => res as Base);
  }

  listSpec(page: number, size: number): Promise<CommoditySpecListResponse> {
    const url = `${this.core.UrlPrefix}/admin/commodity/spec/list`;
    return this.core.get(url, { page: page, size: size, orderBy: 'id', order: 1 }).toPromise()
      .then(res => res as CommoditySpecListResponse);
  }

  // endregion
}
