import { Component, OnInit } from '@angular/core';
import { CommodityService } from '../../../services/commodity.service';
import { ElMessageService } from 'element-angular';
import { CommodityMenu } from '../../../response/commodity';

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

  constructor(private commodity: CommodityService, private message: ElMessageService) { }

  ngOnInit() {
    this.initData();
  }

  private initData() {
    this.commodity.findMenuByParentId(0).then(res => {
      if (res.status === 0) {
        this.list = [];
        this.list = res.list;
      } else {
        this.message.warning(res.message);
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
        this.message.warning(res.message);
      }
    });
  }

  save() {
    console.log(this.parentId);
    this.commodity.saveMenu(this.menu, this.parentId, this.comment).then(res => {
      if (res.status === 0) {
        this.message.success(`[${this.menu}]已经保存`);
        this.initData();
      } else {
        this.message.warning(res.message);
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
        this.message.warning(res.message);
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
      this.message.warning('还不能删除这个栏目，请先删除它的子栏目。');
      return;
    }
    this.commodity.deleteMenu(menu.id).then(res => {
      if (res.status === 0) {
        this.initData();
      } else {
        this.message.warning(res.message);
      }
    });
  }
}
