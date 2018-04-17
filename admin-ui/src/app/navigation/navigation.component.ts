import { Component, OnInit } from '@angular/core';
import { NavigationType } from './navigation-vi';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  viewType = NavigationType.shop;
  title: string;
  index = '1';

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.viewType = Number(this.route.snapshot.paramMap.get('t')) as NavigationType;
    this.title = this.viewType === NavigationType.shop ? '商品' : '文章';
  }

  handle(index: string) {
    this.index = index;
  }
}
