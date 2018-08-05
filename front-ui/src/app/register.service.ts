import { Injectable } from '@angular/core';
import { BaseResponse } from './model/response';
import { CoreService } from './service/core.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  cellphone = '';
  isVerified = false;

  constructor(private cs: CoreService) { }

  sendCode(cellphone: string): Promise<BaseResponse> {
    const url = `${this.cs.Config.apiURI}/front/code`;
    return this.cs.post(url, { cellphone: cellphone }).toPromise()
      .then(x => x as BaseResponse);
  }

  register(cellphone: string, code: string): Promise<BaseResponse> {
    const url = `${this.cs.Config.apiURI}/front/register`;
    return this.cs.post(url, { username: cellphone, cellphone: cellphone, code: code }).toPromise()
      .then(x => x as BaseResponse);
  }
}
