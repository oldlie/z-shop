import { Injectable } from '@angular/core';

@Injectable()
export class CoreService {

  isLogin = false;

  private urlPerfix = 'http://localhost:8080';
  get UrlPrefix() {
    return this.urlPerfix;
  }

  private token = '';
  set Token(token: string) {
    this.token = token;
  }

  private header;
  get Header() {
    return this.header = {'Authorization': `ZShop ${this.token}`};
  }

  constructor() { }

}
