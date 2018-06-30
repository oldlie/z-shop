import { Base } from './response';
import { Commodity } from './commodity.response';

export interface Carousel {
    id: number;
    title: string;
    url: string;
    image: string;
    sequence: number;
    color: string;
}

export interface CarouselListResponse extends Base {
    list: Array<Carousel>;
}

export interface HomeCommodityListReponse extends Base {
    list: Array<Commodity>;
}
