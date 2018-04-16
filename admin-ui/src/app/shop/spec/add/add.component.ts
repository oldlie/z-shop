import { Component, OnInit } from '@angular/core';
import { CommoditySpecVI } from '../../commodity-vi';

@Component({
  selector: 'app-commodity-spec-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  spec: CommoditySpecVI;

  constructor() { }

  ngOnInit() {
    this.spec = {
      title: '',
      spec: '',
      price: 1,
      inventory: 0
    };
  }

}
