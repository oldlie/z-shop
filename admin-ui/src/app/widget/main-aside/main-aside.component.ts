import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-aside',
  templateUrl: './main-aside.component.html',
  styleUrls: ['./main-aside.component.css']
})
export class MainAsideComponent implements OnInit {

  @Input()
  index = '1-1';

  constructor(private router: Router) { }

  ngOnInit() {
    console.log('MainAsideComponent:', this.index);
  }

  goto(url: string) {
    this.router.navigate([url]).catch(err => {
      console.log(err);
    });
  }
}
