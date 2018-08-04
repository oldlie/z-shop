import { Injectable } from '@angular/core';
import { CoreService } from './core.service';
import { BaseResponse } from '../model/response';

@Injectable()
export class FrontService {

    constructor(private coreService: CoreService) { }

    sendCode(cellphone: string): Promise<BaseResponse> {
        const url = `${this.coreService.Config.apiURI}/front/code`;
        return this.coreService.post(url, { cellphone: cellphone }).toPromise()
            .then(x => x as BaseResponse);
    }

    register(cellphone: string, code: string): Promise<BaseResponse> {
        const url = `${this.coreService.Config.apiURI}/front/register`;
        return this.coreService.post(url, { username: cellphone, cellphone: cellphone, code: code }).toPromise()
            .then(x => x as BaseResponse);
    }
}
