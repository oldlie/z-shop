import { Component, OnInit, Input } from '@angular/core';
import { CommodityService } from '../../../../services/commodity.service';

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

  tableData: any[];
  page = 0;
  total = 0;
  size = 10;
  small = true;

  constructor(private commodity: CommodityService) { }

  ngOnInit() {
    this.tableData = [];
    this.commodity.listSpec(0, this.size).then(res => {
      if (res.status === 0) {
        this.tableData = res.list;
        this.total = res.pages;
        console.log(res);
      } else {
        console.log(res.message);
      }
    });
  }

  chose(data: any) {
    console.log('chose', this.tableData[data.index]);
  }

  update(data: any) {
    console.log('update', data);
  }

  delete(data: any) {
    console.log('delete', data);
  }
}
