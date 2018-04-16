import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-main-aside',
  templateUrl: './main-aside.component.html',
  styleUrls: ['./main-aside.component.css']
})
export class MainAsideComponent implements OnInit {

  @Input()
  index = '1-1';

  constructor() { }

  ngOnInit() {
    console.log('MainAsideComponent:', this.index);
  }

}
