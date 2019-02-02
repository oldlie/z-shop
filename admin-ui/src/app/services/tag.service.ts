import { Injectable } from '@angular/core';
import { CoreService } from './core.service';
import { TagList } from '../response/tag';
import { Base } from '../response/response';

@Injectable()
export class TagService {

  constructor(private core: CoreService) { }

  list(page: number, size: number): Promise<TagList> {
    const url = `${this.core.UrlPrefix}/admin/tag/list`;
    return this.core.get(url, {
      page: (this.core.getPage(page)).toString(),
      size: size.toString(),
      orderBy: 'id',
      order: '0'
    })
      .toPromise()
      .then(reponse => reponse as TagList);
  }

  save(title: string): Promise<Base> {
    const url = `${this.core.UrlPrefix}/admin/tag/store`;
    const params = {
      id: '0',
      title: title,
      parentId: '0'
    };
    return this.core.post(url, params).toPromise()
      .then(response => response as Base);
  }

  delete(id: number): Promise<Base> {
    const url = `${this.core.UrlPrefix}/admin/tag/delete`;
    return this.core.post(url, {id: id}).toPromise().then(res => res as Base);
  }
}
