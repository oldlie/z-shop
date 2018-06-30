import { Injectable } from '@angular/core';
import { CoreService } from './core.service';
import { CarouselListResponse, HomeCommodityListReponse } from '../response/home.response';
import { Base } from '../response/response';


@Injectable()
export class HomeService {

    constructor(private core: CoreService) {}

    // region Carousel

    addCarousel(id: number | null, title: string, link: string, image: string, 
        sequence: number = 0, color: string = '#ffffff'): Promise<Base> {
        const url = `${this.core.UrlPrefix}/admin/home-setting/carousel/add`;
        return this.core.post(url, {
            id: id,
            title: title,
            url: link,
            image: image,
            sequence: sequence,
            color: color
        }).toPromise().then(x => x as Base);
    }

    deleteCarousel(id: number): Promise<Base> {
        const url = `${this.core.UrlPrefix}/admin/home-setting/carousel/delete`;
        return this.core.post(url, {id: id}).toPromise().then(x => x as Base);
    }

    listCarousel(): Promise<CarouselListResponse> {
        const url = `${this.core.UrlPrefix}/admin/home-setting/carousel/list`;
        return this.core.get(url, null).toPromise().then(x => x as CarouselListResponse);
    }

    // endregion

    listHomeCommodity(): Promise<HomeCommodityListReponse> {
        const url = `${this.core.UrlPrefix}/admin/home-setting/commodity/list`;
        return this.core.get(url, null).toPromise().then(x => x as HomeCommodityListReponse);
    }
}
