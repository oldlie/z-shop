import { Component, OnInit } from '@angular/core';
import { CommodityStatus } from '../commodity-vi';

@Component({
  selector: 'app-add-commodity',
  templateUrl: './add-commodity.component.html',
  styleUrls: ['./add-commodity.component.css']
})
export class AddCommodityComponent implements OnInit {

  title = '';
  summary = '';
  comment = '';
  description = '';
  status = CommodityStatus.init;

  constructor() { }

  ngOnInit() {
  }

}
