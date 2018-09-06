import { BaseResponse } from './response';

export interface SimpleResponse<T> extends BaseResponse {
    item: T;
}
