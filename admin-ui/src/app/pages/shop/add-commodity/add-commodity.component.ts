import { Component, OnInit } from '@angular/core';
import { CommodityStatus, CommodityVI, CommoditySpecVI } from '../commodity-vi';
import { ElNotificationService } from 'element-angular';
import { CommoditySpec, CommodityMenu } from '../../../response/commodity';
import { Tag } from '../../../response/tag';
import { CommodityService } from '../../../services/commodity.service';

@Component({
  selector: 'app-add-commodity',
  templateUrl: './add-commodity.component.html',
  styleUrls: ['./add-commodity.component.css'],
  providers: [
    CommodityService,
  ]
})
export class AddCommodityComponent implements OnInit {

  commodityVI: CommodityVI;

  id = 0;
  title = '';
  summary = '';
  comment = '';
  description = '';
  status = CommodityStatus.init;
  showSpecDialog = false;
  showMenuDialog = false;
  showTagDialog = true;

  specifciationFlag = 1;
  specificationList = new Array<CommoditySpecVI>();
  tagList = new Array<Tag>();
  menuList = new Array<CommodityMenu>();
  menuSourceList = new Array<CommodityMenu>();
  menuParentId = 0;
  refreshMenu = false;
  tag = '';

  constructor(private commodity: CommodityService,
    private notify: ElNotificationService) { }

  ngOnInit() {
    this.commodityVI = {
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
    this.specifciationFlag = 1;
  }

  checkSpecListener(specification: CommoditySpecVI) {
    this.specificationList.push(specification);
    this.showSpecDialog = false;
  }

  deleteSpec(specification: CommoditySpecVI) {
    console.log('deleteSpec', this.specificationList);
    for (let i = 0; i < this.specificationList.length; i++) {
      const item = this.specificationList[i];
      console.log(i, item);
      if (specification.id === item.id) {
        this.specificationList.splice(i, 1);
        return;
      }
    }
  }

  addMenu() {
    this.refreshMenu = false;
    this.menuList = [];
    this.commodity.findMenuByParentId(0).then(res => {
      if (res.status === 0) {
        this.menuSourceList = [];
        this.menuSourceList = res.list;
        console.log('addMenu', res);
        this.showMenuDialog = true;
      } else {
        console.log(res.message);
      }
    });
  }

  cc(data: CommodityMenu) {
    this.menuList.push(data);
    console.log(this.menuList);
    this.showMenuDialog = false;
    this.refreshMenu = true;
  }

  ccc(data: CommodityMenu) {
    this.menuList.push(data);
    this.commodity.findMenuByParentId(data.id).then(res => {
      if (res.status === 0) {
        this.menuSourceList = [];
        this.menuSourceList = res.list;
      } else {
        console.log(res.message);
      }
    });
  }

  checkMenuRoot() {
    this.commodity.findMenuByParentId(0).then(res => {
      if (res.status === 0) {
        this.menuList = [];
        this.menuSourceList = [];
        this.menuSourceList = res.list;
      } else {
        console.log(res.message);
      }
    });
  }

  addTag() {
    this.showTagDialog = true;
  }
}
