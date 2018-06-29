import { Component, OnInit } from '@angular/core';
import { Commodity } from '../../../response/commodity.response';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  index = 1;
  model: Commodity;

  constructor() { }

  ngOnInit() {
  }

  editCommdoity(model: Commodity) {
    this.index = 1;
    this.model = model;
  }

  handle(index: number) {
    if (index === 0) {
      this.model = null;
    }
  }
}
