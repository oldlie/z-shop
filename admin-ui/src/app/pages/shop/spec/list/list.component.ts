import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { CommodityService } from '../../../../services/commodity.service';
import { CommoditySpec } from '../../../../response/commodity';
import { CommoditySpecVI } from '../../commodity-vi';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-commodity-spec-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css'],
  providers: [
    CommodityService,
  ]
})
export class ListComponent implements OnInit {

  @Input()
  commodityId = 0;
  @Output()
  editEvent = new EventEmitter<CommoditySpecVI>();
  @Output()
  checkEvent = new EventEmitter<CommoditySpecVI>();

  tableData: any[];
  page = 0;
  total = 0;
  size = 10;
  small = true;
  loading = false;

  constructor(private commodity: CommodityService, private message: NzMessageService) { }

  ngOnInit() {
    console.log('commodity list', this.commodityId);
    this.tableData = [];
    this.commodity.listSpec(0, this.size).then(res => {
      if (res.status === 0) {
        this.tableData = res.list;
        this.total = res.pages;
        console.log('total', this.total);
      } else {
        console.log(res.message);
      }
    });
  }

  chose(data: CommoditySpec) {
    console.log('chose', data);
    this.checkEvent.emit(data);
  }

  edit(data: CommoditySpec) {
    const vi = {
      id: data.id,
      title: data.title,
      breed: data.breed,
      origin: data.origin,
      feature: data.feature,
      spec: data.spec,
      store: data.store,
      productDatetime: data.productDatetime,
      price: data.price,
      inventory: data.inventory
    } as CommoditySpecVI;
    this.editEvent.emit(vi);
  }

  delete(data: CommoditySpec) {
    this.total = 0; // 刷新分页组件
    this.commodity.deleteSpec(data.id).then(res => {
      if (res.status === 0) {
        this.loadData(this.page);
      } else {
        this.message.create('warning', res.message);
      }
    });
  }

  loadData(page: number) {
    this.commodity.listSpec(page, this.size).then(res => {
      this.tableData = [];
      if (res.status === 0) {
        this.tableData = res.list;
        this.total = res.pages;
      } else {
        console.log(res.message);
      }
    });
  }

  searchData(reset: boolean = false) {
    if (reset) {
      this.page = 1;
    }
    this.loading = true;
    this.commodity.listSpec(this.page, this.size).then(res => {
      this.loading = false;
      this.tableData = [];
      if (res.status === 0) {
        this.tableData = res.list;
        this.total = +res.pages * this.size;
      } else {
        console.log(res.message);
      }
    });
  }
}
