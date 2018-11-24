import { CommodityInfo } from './response';

export interface AuthorityVI {
    id: number;
    role: string;
}

export interface ArticleVI {
    id: number;
    title: string;
    summary: string;
    viewCount: number;
    agreeCount: number;
    commentCount: number;
    image: string;
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
    publishAt?: string | Date;
}

export interface LoginVI {
    id?: number;
    account?: string;
    token?: string;
    isLogin: boolean;
}

export interface UserVI {
    id?: number;
    username: string;
    password?: string;
    userNickname: string;
    cellphone: string;
    cellphone2: string;
    resume: string;
    image: string;
    isInit: number;
    money?: number;
    authorities?: Array<AuthorityVI>;
}
