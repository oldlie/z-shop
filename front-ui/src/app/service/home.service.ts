import { Injectable } from '@angular/core';
import { CoreService } from './core.service';
import { CarouselListResponse, CommodityInfoListResponse, ArticleListResponse } from '../model/response';

@Injectable()
export class HomeService {

  constructor(private coreService: CoreService) { }

  initCarousel(): Promise<CarouselListResponse> {
    const url = `${this.coreService.Config.apiURI}/home/carousel`;
    return this.coreService.get(url, null).toPromise().then(x => x as CarouselListResponse);
  }

  initCommodity(): Promise<CommodityInfoListResponse> {
    const url = `${this.coreService.Config.apiURI}/home/commodity`;
    return this.coreService.get(url, null).toPromise().then(x => x as CommodityInfoListResponse);
  }

  initArticle(page: number, size: number, orderBy: string = 'id',
    order: number = 0): Promise<ArticleListResponse> {
    const url = `${this.coreService.Config.apiURI}/home/article`;
    return this.coreService.get(url, { page: page, size: size, orderBy: orderBy, order: order })
      .toPromise().then(x => x as ArticleListResponse);
  }
}
