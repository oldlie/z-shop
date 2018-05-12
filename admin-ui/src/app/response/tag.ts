import { List } from './response';

export interface Tag {
    id: number;
    title: string;
    parentId: number;
}

export interface TagList extends List {
    tagList: Array<Tag>;
}
