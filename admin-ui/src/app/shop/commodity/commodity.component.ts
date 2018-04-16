import { Component, OnInit } from '@angular/core';
import { CommodityVI } from '../commodity-vi';

@Component({
  selector: 'app-commodity',
  templateUrl: './commodity.component.html',
  styleUrls: ['./commodity.component.css']
})
export class CommodityComponent implements OnInit {

  list: Array<CommodityVI>;
  page = 1;
  total: number;

  constructor() { }

  ngOnInit() {
  }

}
