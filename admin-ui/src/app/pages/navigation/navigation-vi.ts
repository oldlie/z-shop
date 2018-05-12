export interface NavigationVi {
    id: number;
    title: string;
    parentId: number;
    comment?: string;
}

export enum NavigationType {
    shop = 0,
    aritcle = 1
}
