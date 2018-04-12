import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';

  constructor(private router: Router) {}

  ngOnInit() {
    console.log('ready goto login page!');
    this.router.navigate(['/login']).catch(err => {
      console.log('navigate to /login', err);
    });
  }
}
