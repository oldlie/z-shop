import { Injectable } from '@angular/core';
import { Base } from '../response/response';
import { CoreService } from './core.service';

@Injectable()
export class CommodityService {

  constructor(private core: CoreService) { }

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
    return this.core.post(url, {id: id}).toPromise().then(res => res as Base);
  }
}
