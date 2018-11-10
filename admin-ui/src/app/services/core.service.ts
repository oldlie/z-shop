import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CoreService {

  isLogin = false;

  private urlPerfix = 'http://localhost:8080';
  get UrlPrefix() {
    return this.urlPerfix;
  }

  private resourceURI = 'http://localhost/zs/resource';
  get ResourceURI() {
    return this.resourceURI;
  }

  private token = '';
  set Token(token: string) {
    this.token = token;
  }

  private header;
  get Header() {
    return this.header = {'Authorization': `ZShop ${this.token}`, 'Content-Type': 'application/json'};
  }

  public account = '';

  constructor(private http: HttpClient) { }

  getPage(page: number) {
    return page > 1 ? page - 1 : 0;
  }

  get(url: string, params: any): Observable<Object> {
    return this.http.get(url, {
      headers: this.Header,
      params: params,
    });
  }

  post(url: string, body: any):  Observable<Object> {
    return this.http.post(url, body, {headers: this.Header});
  }

  fomartMoney(origin: number): String {
    const yuan = window['parseInt'](origin / 1000);
    let yu = origin % 1000;
    const jiao = window['parseInt'](yu / 100);
    yu = yu % 100;
    const fen =  window['parseInt'](yu / 10);
    return `${yuan}.${jiao}${fen}`;
  }
}
