import { Tag } from './tag';
import { List } from './response';

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
}

export enum ArticleStatus {
    draft = 0,
    public = 1,
    private = 2
}

export interface ArticleListResponse extends List {
    list: Array<Article>;
}
