import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CoreService } from './services/core.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'z-shop';
  defaultAsideIndex = '6-1';
  isLogin = false;

  private startPageUrl = '/login';

  constructor(public core: CoreService, private router: Router) {}

  ngOnInit() {
    console.log(this.isLogin);
    if (this.core.isLogin) {
      console.log('"wo le go qu"');
      console.log('ready goto login page!');
      this.router.navigate([this.startPageUrl]).catch(err => {
        console.log('navigate to /login', err);
      });
    }

  }
}
