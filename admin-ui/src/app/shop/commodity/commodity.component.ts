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
  filterKey: string;

  constructor() { }

  ngOnInit() {
    const now = new Date();
    this.list = [
      {id: 1, title: 'commodity 1', summary: 'summary 1', description: '', status: 0, ranking: 0, rankingCount: 0, shareCount: 0,
        viewCount: 0, comment: '', createAt: now, updateAt: now},
      {id: 2, title: 'commodity 2', summary: 'summary 2', description: '', status: 0, ranking: 0, rankingCount: 0, shareCount: 0,
        viewCount: 0, comment: '', createAt: now, updateAt: now},
      {id: 3, title: 'commodity 3', summary: 'summary 3', description: '', status: 0, ranking: 0, rankingCount: 0, shareCount: 0,
        viewCount: 0, comment: '', createAt: now, updateAt: now},
    ];
  }

  edit(scope: any) {

  }

  delete(scope: any) {

  }
}
