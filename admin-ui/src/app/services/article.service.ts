import { Injectable } from "@angular/core";
import { CoreService } from "./core.service";
import { Base } from "../response/response";
import { ArticleMenuListResponse, ArticleStatus, ArticleListResponse } from "../response/article.response";
import { ArticleVI } from "../pages/article/article.vi";

@Injectable()
export class ArticleService {

    constructor(private core: CoreService){}

    // region Menu
    saveMenu(title: String, parentId: number, comment: String): Promise<Base> {
        const url = `${this.core.UrlPrefix}/admin/article/menu/store`;
        return this.core.post(url, {
          id: 0,
          title: title,
          parentId: parentId,
          children: 0,
          comment: comment
        }).toPromise().then(res => res as Base);
    }

    deleteMenu(id: number): Promise<Base> {
        const url = `${this.core.UrlPrefix}/admin/article/menu/delete`;
        return this.core.post(url, { id: id }).toPromise().then(res => res as Base);
    }

    childrenMenu(parentId: number): Promise<ArticleMenuListResponse> {
        const url = `${this.core.UrlPrefix}/admin/article/menu/children`;
        return this.core.post(url, { id: parentId }).toPromise().then(res => res as ArticleMenuListResponse);
    }
    // endregion

    // region Article
    save(request: ArticleVI): Promise<Base> {
        const url = `${this.core.UrlPrefix}/admin/article/store`;
        return this.core.post(url, request).toPromise().then(x => x as Base);
    }

    delete(id: number): Promise<Base> {
        const url = `${this.core.UrlPrefix}/admin/article/delete`;
        return this.core.post(url, {id: id}).toPromise().then(x => x as Base);
    }

    load(page: number, size: number, orderBy: string, order: number): Promise<ArticleListResponse> {
        const url = `${this.core.UrlPrefix}/admin/article/list`;
        return this.core.get(url, {page: page, size: size, orderBy: orderBy, order: order})
        .toPromise().then(x => x as ArticleListResponse)
    }
    // endregion
}
