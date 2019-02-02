import { Component, OnInit } from '@angular/core';
import { CommodityService } from '../../../services/commodity.service';
import { NzMessageService } from 'ng-zorro-antd';
import { CommodityMenu } from '../../../response/commodity.response';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  providers: [
    CommodityService,
  ]
})
export class MenuComponent implements OnInit {

  toggle = false;
  menu = '';
  parentId = 0;
  parent = '根目录';
  comment = '';
  childrenList: Array<CommodityMenu> = [];
  list: Array<CommodityMenu> = [];
  childrenListShow = false;
  mouseOnId = 0;

  constructor(private commodity: CommodityService, private message: NzMessageService) { }

  ngOnInit() {
    this.initData();
  }

  private initData() {
    this.commodity.findMenuByParentId(0).then(res => {
      if (res.status === 0) {
        this.list = [];
        this.list = res.list;
      } else {
        this.message.create('warning', res.message);
      }
    });
  }

  selectParent() {
    this.commodity.findMenuByParentId(0).then(res => {
      if (res.status === 0) {
        this.toggle = true;
        this.childrenList = [];
        this.childrenList = res.list;
      } else {
        this.message.create('warning', res.message);
      }
    });
  }

  save() {
    if (this.menu === '') {
      this.message.warning('请填写栏目名称。');
      return;
    }
    this.commodity.saveMenu(this.menu, this.parentId, this.comment).then(res => {
      if (res.status === 0) {
        this.message.success(`[${this.menu}]已经保存`);
        this.initData();
      } else {
        this.message.create('warning', res.message);
      }
    });
  }

  cc(data: CommodityMenu) {
    this.parentId = data.id;
    this.parent = data.title;
    this.toggle = false;
  }

  ccc(id: number) {
    this.commodity.findMenuByParentId(id).then(res => {
      if (res.status === 0) {
        this.childrenList = [];
        this.childrenList = res.list;
      } else {
        this.message.create('warning', res.message);
      }
    });
  }

  childMenu(menu: CommodityMenu) {
    this.commodity.findMenuByParentId(menu.id).then(res => {
      if (res.status === 0) {
        this.mouseOnId = menu.id;
        this.childrenList = [];
        this.childrenList = res.list;
      } else {
        console.log(res.message);
      }
    });
    return false;
  }

  ml() {
    this.mouseOnId = 0;
  }

  delete(menu: CommodityMenu) {
    if (menu.children > 0) {
      this.message.create('warning', '还不能删除这个栏目，请先删除它的子栏目。');
      return;
    }
    this.commodity.deleteMenu(menu.id).then(res => {
      if (res.status === 0) {
        this.initData();
      } else {
        this.message.create('warning', res.message);
      }
    });
  }

  closeDialog() {
    this.parentId = 0;
    this.parent = '根目录';
    this.toggle = false;
  }
}
