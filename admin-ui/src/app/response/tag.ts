import { Page } from './response';

export interface Tag {
    id: number;
    title: string;
    parentId: number;
}

export interface TagList extends Page {
    tagList: Array<Tag>;
}
