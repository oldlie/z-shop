import { Base, List } from './response';

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

export interface CommoditySpecListResponse extends List {
    list: Array<CommoditySpec>;
}
