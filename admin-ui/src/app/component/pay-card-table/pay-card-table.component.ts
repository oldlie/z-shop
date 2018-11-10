import { Component, OnInit } from '@angular/core';
import { PayCard } from '../../response/pay-card';
import { CoreService } from '../../services/core.service';
import { PageResponse } from '../../response/response';

@Component({
  selector: 'app-pay-card-table',
  templateUrl: './pay-card-table.component.html',
  styleUrls: ['./pay-card-table.component.css']
})
export class PayCardTableComponent implements OnInit {

  dataSet: Array<PayCard>;
  total = 0;
  page = 1;
  size = 10;
  orderBy = 'id';
  order = 'desc';

  constructor(private cs: CoreService) { }

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    const url = `${this.cs.UrlPrefix}/admin/finance/pay-cards`;
    this.cs.get(url, {
      page: this.page,
      size: this.size,
      orderBy: this.orderBy,
      order: this.order
    }).toPromise().then((x: PageResponse<PayCard>) => {
      if (x.status === 0) {
        this.dataSet = x.list;
        this.total = x.total;
      } else {
        console.error('Pay card table load data:', x);
      }
    }).catch(err => console.error(err));
  }

  edit(item: PayCard) {}

  delete(item: PayCard) {}
}
