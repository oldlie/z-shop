import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CoreService } from './service/core.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';

  constructor(public coreService: CoreService, private router: Router) {}

  ngOnInit() {
    // this.router.navigate(['/article', 26]).catch(err => { console.log(err); });
  }

  logout() {
    this.coreService.LoginInfo.isLogin = false;
    this.coreService.LoginInfo.account = '';
    this.coreService.LoginInfo.token = '';
  }
}
