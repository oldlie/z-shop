import { Component, OnInit } from '@angular/core';
import { CommodityStatus, CommodityVI } from '../commodity-vi';

@Component({
  selector: 'app-add-commodity',
  templateUrl: './add-commodity.component.html',
  styleUrls: ['./add-commodity.component.css']
})
export class AddCommodityComponent implements OnInit {

  commodity: CommodityVI;

  id = 0;
  title = '';
  summary = '';
  comment = '';
  description = '';
  status = CommodityStatus.init;
  showSpecDialog = false;

  constructor() { }

  ngOnInit() {
    this.commodity = {
      id: 0,
      title: '',
      summary: '',
      comment: '',
      description: '',
      status: 0,
      ranking: 0,
      rankingCount: 0,
      viewCount: 0,
      shareCount: 0
    };
  }

  addSpec() {
    this.showSpecDialog = true;
  }
}
