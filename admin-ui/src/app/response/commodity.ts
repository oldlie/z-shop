import { Base, Page } from './response';
import { Tag } from './tag';

export interface CommodityMenu {
    id: number;
    title: string;
    parentId: number;
    children: number;
    comment: string;
}

export interface CommodityMenuListResponse extends Base {
    list: Array<CommodityMenu>;
}

export interface CommoditySpec {
    id: number;
    title: string;
    commodityId: number;
    breed: string;
    origin: string;
    feature: string;
    spec: string;
    store: string;
    productDatetime: string;
    price: number;
    inventory: number;
}

export interface CommoditySpecListResponse extends Page {
    list: Array<CommoditySpec>;
}


export interface Commodity {
    id: number;
    title: string;
    summary: string;
    comment?: string;
    description: string;
    status: number;
    ranking: number;
    rankingCount: number;
    viewCount: number;
    shareCount: number;
    createAt?: Date;
    updateAt?: Date;
    tags?: Array<Tag>;
    specifications?: Array<CommoditySpec>;
    menus?: Array<CommodityMenu>;
}

export interface CommodityListResponse extends Page {
    list: Array<Commodity>;
}
