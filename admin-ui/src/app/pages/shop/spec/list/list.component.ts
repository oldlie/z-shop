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

  constructor(private commodity: CommodityService) { }

  ngOnInit() {
    this.tableData = [];
    this.commodity.listSpec(0, 10).then(res => {
      if (res.status === 0) {
        this.tableData = res.list;
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
