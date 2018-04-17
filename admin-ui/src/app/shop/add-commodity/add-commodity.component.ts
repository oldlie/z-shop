import { Component, OnInit } from '@angular/core';
import { CommodityStatus, CommodityVI } from '../commodity-vi';
import { ElNotificationService } from 'element-angular';

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

  constructor(private notify: ElNotificationService) { }

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
    if (this.title === '') {
      this.notify['warning']('添加规格前请先填写商品名称。');
      return;
    }
    this.showSpecDialog = true;
  }

}
