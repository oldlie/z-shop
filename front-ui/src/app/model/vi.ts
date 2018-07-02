export interface LoginVI {
    id?: number;
    account?: string;
    token?: string;
    isLogin: boolean;
}

export interface ConfigVI {
    apiURI: string;
    resourceURI: string;
}
