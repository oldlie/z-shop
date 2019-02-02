import { BaseResponse } from './response';

export interface LoginResponse extends BaseResponse {
    token: string;
}
