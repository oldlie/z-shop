import { Component, OnInit } from '@angular/core';
import { CommodityVI } from '../../model/vi';

@Component({
  selector: 'app-commodity-list',
  templateUrl: './commodity-list.component.html',
  styleUrls: ['./commodity-list.component.css']
})
export class CommodityListComponent implements OnInit {

  commodityList = [] as Array<CommodityVI>;

  constructor() { }

  ngOnInit() {
  }

}
