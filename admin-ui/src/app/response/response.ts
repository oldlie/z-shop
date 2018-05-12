export interface Base {
    status: number;
    message?: string;
    path?: string;
    error?: string;
    timestamp?: string;
}

export interface List extends Base {
    total: number;
    pages: number;
    orderBy?: string;
    order?: number;
}
