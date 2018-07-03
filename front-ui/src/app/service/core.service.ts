import { Injectable } from '@angular/core';
import { LoginVI, ConfigVI } from '../model/vi';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoreService {

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
    return this.header = {'Authorization': `ZShop ${this.loginInfo.token}`, 
    'Content-Type': 'application/json'};
  }

  constructor(private http: HttpClient) { }

  get(url: string, params: any): Observable<Object> {
    return this.http.get(url, {
      headers: this.Header,
      params: params,
    });
  }

  post(url: string, body: any):  Observable<Object> {
    return this.http.post(url, body, {headers: this.Header});
  }
}
