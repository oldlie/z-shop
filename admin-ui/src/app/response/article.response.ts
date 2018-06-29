import { Tag } from './tag';
import { Page } from './response';

/**
 * 遇到了一个文件命名的问题
 * 这个文件最初命名为 article.ts, import 语句中后半段为 ../../../article
 * 同时，有个articleService 命名为 article
 * 这时，jsrt 会将两个article等同，造成provides中出现循环引用的错误 
 */

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

export enum ArticleStatus {
    draft = 0,
    public = 1,
    private = 2
}

export interface ArticleListResponse extends Page {
    list: Array<Article>;
}

export interface ArticleMenu {
    id?: number;
    title: string;
    parentId: number;
    comment?: string;
    sequence?: number;
    children?: number;
}

export interface ArticleMenuListResponse extends Page {
    list: Array<ArticleMenu>;
}