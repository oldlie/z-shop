import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Commodity } from '../../../response/commodity.response';
import { CommodityService } from '../../../services/commodity.service';
import { NzNotificationService } from 'ng-zorro-antd';

@Component({
  selector: 'app-commodity',
  templateUrl: './commodity.component.html',
  styleUrls: ['./commodity.component.css'],
  providers: [
    CommodityService,
  ]
})
export class CommodityComponent implements OnInit {

  list = new Array<Commodity>();
  page = 0;
  size = 10;
  pages = 0;
  orderBy = 'id';
  order = 0;
  filterKey: string;
  @Output()
  updateEvent = new EventEmitter<Commodity>();

  constructor(private commodity: CommodityService, private notify: NzNotificationService) { }

  ngOnInit() {
    const now = new Date();
    this.findAll();
  }

  add2Home(item: Commodity) {

  }

  edit(item: Commodity) {
    this.updateEvent.emit(item);
  }

  delete(item: Commodity) {
    this.commodity.delete(item.id).then(res => {
      if (res.status === 0) {
        const temp = this.list.filter(d => d.id !== item.id);
        this.list = temp;
        this.notify.success('提示', '已删除');
      } else {
        this.notify.warning('提示', res.message);
      }
    });
  }

  loadData(page: number) {
    this.page = page;
    this.findAll();
  }

  private findAll() {
    this.commodity.list(this.page, this.size, this.orderBy, this.order).then(res => {
      if (res.status === 0) {
        this.list = res.list;
        this.pages = res.pages;
      } else {
        this.notify.warning('提示', res.message);
      }
    });
  }
}
