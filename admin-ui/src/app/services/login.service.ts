import { Injectable } from '@angular/core';
import { ResponseData, urlPrefix } from '../common/response-data';
import { HttpClient } from '@angular/common/http';
import { CoreService } from './core.service';

@Injectable()
export class LoginService {

  private isInit = false;

  constructor(private core: CoreService,
    private http: HttpClient) { }

  /**
   * URL: /login
   * FORM DATA: { "username": "", "password": "" }
   * RESPONSE: { "status": "0", "msg": "", "token": "" }
   *
   * @param {string} username
   * @param {string} password
   * @return {Promise<LoginResponse>} result
   */
  login(username: string, password: string): Promise<ResponseData> {
    const url = `${this.core.UrlPrefix}/login`;

    /*
    const formData = {
      username: username,
      password: password
    };
    */
   const formData = {
    username: 'admin@zshop.com',
    password: '123456'
  };

    return this.http.post(url, JSON.stringify(formData)).toPromise()
      .then((response: ResponseData) => {
        this.core.isLogin = response.status === 0;
        this.core.Token = response.token;
        return response;
      });
  }
}

export interface LoginResponse extends ResponseData {
  token: string;
}
