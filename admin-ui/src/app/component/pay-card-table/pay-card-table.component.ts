import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { PayCard } from '../../response/pay-card';
import { CoreService } from '../../services/core.service';
import { PageResponse, Base } from '../../response/response';
import { FinanceService } from '../../services/finance.service';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-pay-card-table',
  templateUrl: './pay-card-table.component.html',
  styleUrls: ['./pay-card-table.component.css'],
  providers: [
    FinanceService
  ]
})
export class PayCardTableComponent implements OnInit {

  dataSet: Array<PayCard>;
  loading = false;
  total = 0;
  page = 1;
  size = 10;
  orderBy = 'id';
  order = 'desc';
  sortKey: string;
  sortValue: string;

  searchGenderList: string[] = [];
  isVisible = false;
  payCard: PayCard;

  constructor(private cs: CoreService, private fs: FinanceService,
    private msg: NzMessageService) { }

  ngOnInit() {
    this.loadData();
  }

  formatMoney(val: number) {
    return this.cs.fomartMoney(val);
  }

  isSold(val: number) {
    return val === 1 ? '已售' : '未售';
  }

  isUsed(val: number) {
    return val === 1 ? '已充值' : '未使用';
  }

  loadData(reset: boolean = false) {
    if (reset) {
      this.page = 1;
    }
    this.loading = true;
    this.fs.listPayCards(this.page, this.size, this.orderBy, this.order)
      .then((x: PageResponse<PayCard>) => {
        if (x.status === 0) {
          this.dataSet = x.list;
          this.total = x.total;
        } else {
          console.error('Pay card table load data:', x);
        }
      }).catch(err => console.error('load data:', err));
  }

  updateFilter(value: string[]): void {
    this.searchGenderList = value;
    this.loadData(true);
  }

  sort(sort: { key: string, value: string }): void {
    this.orderBy = sort.key;
    this.order = sort.value === 'descend' ? 'desc' : 'asc';
    this.sortKey = sort.key;
    this.sortValue = sort.value;
    this.loadData();
  }

  checkItem(item: PayCard) {
    console.log('Checked item:', item);
  }

  sold(item: PayCard) {
    this.payCard = item;
    this.isVisible = true;
  }

  delete(item: PayCard) {
    const url = `${this.cs.UrlPrefix}/admin/finance/pay-card`;
    this.cs.delete(url, { id: item.id }).toPromise().then((x: Base) => {
      if (x.status === 0) {
        this.msg.success('已无效。');
        item.isValid = 0;
        // this.dataSet = this.dataSet.filter(_item => _item.id !== item.id);
      }
    });
  }

  handleCancel() {
    this.isVisible = false;
  }
}
