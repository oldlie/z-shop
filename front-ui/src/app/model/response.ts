export interface BaseResponse {
    status: number;
    message: string;
}

export interface PageResponse extends BaseResponse {
    total: number;
    pages: number;
    orderBy?: string;
    order?: number;
}

export interface Carousel {
    id: number;
    title: string;
    url: string;
    image: string;
    sequence: number;
    color: string;
}

export interface CarouselListResponse extends BaseResponse {
    list: Array<Carousel>;
}

export interface Tag {
    id: number;
    title: string;
    parentId: number;
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

export interface CommodityMenu {
    id: number;
    title: string;
    parentId: number;
    children: number;
    comment: string;
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

export interface CommodityImage {
    id: number;
    commodityId: number;
    imagePath: string;
}

export interface CommodityInfo {
    commodity: Commodity;
    images?: Array<CommodityImage>;
}

export interface CommodityInfoListResponse extends BaseResponse {
    list: Array<CommodityInfo>;
}

export enum ArticleStatus {
    draft = 0,
    public = 1,
    private = 2
}

export interface ArticleMenu {
    id?: number;
    title: string;
    parentId: number;
    comment?: string;
    sequence?: number;
    children?: number;
}

export interface Article {
    id?: number;
    title: string;
    summary: string;
    imageUrl: string;
    author: string;
    authorId?: number;
    content: string;
    status: number;
    viewCount?: number;
    agreeCount?: number;
    shareCount?: number;
    allowComment?: boolean;
    ranking?: number;
    rankingCount?: number;
    createAt?: Date;
    updateAt?: Date;
    publishAt?: Date;
    tags?: Array<Tag>;
    menus?: Array<ArticleMenu>;
}

export interface ArticleMenuListResponse extends PageResponse {
    list: Array<ArticleMenu>;
}

export interface ArticleListResponse extends PageResponse {
    list: Array<Article>;
}