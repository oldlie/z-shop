import { Injectable } from '@angular/core';
import {ResponseData, urlPrefix} from '../common/response-data';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) { }

  /**
   * URL: /login
   * FORM DATA: { "username": "", "password": "" }
   * RESPONSE: { "status": "0", "msg": "", "token": "" }
   *
   * @param {string} username
   * @param {string} password
   * @return {Promise<LoginResponse>} result
   */
  login(username: string, password: string): Promise<LoginResponse> {
    const url = `${urlPrefix}/login`;
    const formData = {
      username: username,
      password: password
    };
    return Promise.resolve({
      status: 0,
      msg: 'success',
      token: 'hahahahahaa'
    } as LoginResponse);
  }
}

export interface LoginResponse extends ResponseData{
  token: string;
}
