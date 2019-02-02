import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CoreService } from './service/core.service';
import { LoginVI, UserVI } from './model/vi';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  nickname = '';

  constructor(public coreService: CoreService, private router: Router) { }

  ngOnInit() {
    // this.router.navigate(['/article', 26]).catch(err => { console.log(err); });
    const loginInfo = window.localStorage.getItem('loginInfo');
    if (loginInfo) {
      const obj = JSON.parse(loginInfo) as LoginVI;
      this.coreService.LoginInfo.id = obj.id;
      this.coreService.LoginInfo.isLogin = obj.isLogin;
      this.coreService.LoginInfo.account = obj.account;
      this.coreService.LoginInfo.token = obj.token;
      const profile = window.localStorage.getItem('profile');
      const user = JSON.parse(profile) as UserVI;
      this.nickname = user.userNickname;
    }
  }

  logout() {
    this.coreService.LoginInfo.isLogin = false;
    this.coreService.LoginInfo.account = '';
    this.coreService.LoginInfo.token = '';
    window.localStorage.clear();
  }
}
