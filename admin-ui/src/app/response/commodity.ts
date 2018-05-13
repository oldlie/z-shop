import { Base } from './response';

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
