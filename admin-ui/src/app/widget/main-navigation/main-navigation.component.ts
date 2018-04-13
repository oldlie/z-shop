import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main-navigation',
  templateUrl: './main-navigation.component.html',
  styleUrls: ['./main-navigation.component.css']
})
export class MainNavigationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  handle(index: string) {
    console.log(index);
  }
}
