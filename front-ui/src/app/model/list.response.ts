import { BaseResponse } from './response';

export interface ListResponse<T> extends BaseResponse {
    list: Array<T>;
}
