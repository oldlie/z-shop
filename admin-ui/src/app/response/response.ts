export interface Base {
    status: number;
    message?: string;
    path?: string;
    error?: string;
    timestamp?: string;
}

export interface SimpleResponse<T> extends Base {
    item: T;
}

export interface ListResponse<T> extends Base {
    list: Array<T>;
}

export interface PageResponse<T> extends ListResponse<T> {
    total: number;
    pages: number;
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

export interface Page extends Base {
    total: number;
    pages: number;
    orderBy?: string;
    order?: number;
}

export interface UploadFile {
    id: number;
    path: string;
    name: string;
    creatorId?: number;
    creator?: string;
    createAt?: string;
}

export interface FileResponse extends Base {
    list: Array<UploadFile>;
}
