export interface Base {
    status: number;
    message?: string;
    path?: string;
    error?: string;
    timestamp?: string;
}

export interface ResponseData {
    status: number;
    message: string;
    token: string;
}

export interface ValidateResult {
    status: string;
    message?: string;
}

export interface List extends Base {
    total: number;
    pages: number;
    orderBy?: string;
    order?: number;
}
