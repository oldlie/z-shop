import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'z-shop';
  isLogin = false;
  defaultAsideIndex = '3-3';

  private startPageUrl = '/commodity/spec';

  constructor(private router: Router) {}

  ngOnInit() {
    console.log('ready goto login page!');
    this.router.navigate([this.startPageUrl]).catch(err => {
      console.log('navigate to /login', err);
    });
  }
}
