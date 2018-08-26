import { Injectable } from '@angular/core';
import { LoginVI, ConfigVI, UserVI } from '../model/vi';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginResponse } from '../model/login.response';
import { SimpleResponse } from '../model/simple.response';

@Injectable({
  providedIn: 'root'
})
export class CoreService {

  profileInfo: UserVI;
  private loginInfo: LoginVI = {
    token: '',
    isLogin: false
  };
  get LoginInfo() {
    return this.loginInfo;
  }

  private config: ConfigVI = {
    apiURI: 'http://localhost:8080',
    resourceURI: 'http://localhost:80/zs/resource'
  };
  get Config() {
    return this.config;
  }

  private header;
  get Header() {
    return this.header = {
      'Authorization': `ZShop ${this.loginInfo.token}`,
      'Content-Type': 'application/json'
    };
  }

  constructor(private http: HttpClient) { }

  get(url: string, params: any): Observable<Object> {
    return this.http.get(url, {
      headers: this.header,
      params: params
    });
  }

  post(url: string, body: any): Observable<Object> {
    return this.http.post(url, body, { headers: this.Header });
  }

  delete(url: string, params: any): Observable<Object> {
    return this.http.delete(url, {
      headers: this.header,
      params: params
    });
  }

  login(username: string, password: string): Promise<LoginResponse> {
    const url = `${this.config.apiURI}/login`;
    const formData = {
      username: username,
      password: password
    };
    return this.post(url, JSON.stringify(formData)).toPromise()
      .then(x => {
        const response = x as LoginResponse;
        if (response.status === 0) {
          this.loginInfo.account = username;
          this.loginInfo.token = response.token;
          this.loginInfo.isLogin = true;
          window.localStorage.setItem('loginInfo', JSON.stringify(this.loginInfo));
        }
        return response;
      });
  }

  initProfile(username: string) {
    const url = `${this.config.apiURI}/front/profile`;
    return this.get(url, {account: username}).toPromise().then(x => {
      const response = x as SimpleResponse<UserVI>;
      response.item.password = null;
      this.profileInfo = response.item;
      window.localStorage.setItem('profile', JSON.stringify(this.profileInfo));
    });
  }
}
