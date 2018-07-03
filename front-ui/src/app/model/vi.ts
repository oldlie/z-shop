import { CommodityInfo } from "./response";

export interface LoginVI {
    id?: number;
    account?: string;
    token?: string;
    isLogin: boolean;
}

export interface ConfigVI {
    apiURI: string;
    resourceURI: string;
}

export interface CarouselVI {
    title: string;
    image: string;
    url: string;
}

export interface CommodityVI {
    id: number;
    title: string;
    desc: string;
    image: string;
    info: CommodityInfo;
}
