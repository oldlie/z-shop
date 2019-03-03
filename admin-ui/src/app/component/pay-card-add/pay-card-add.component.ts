import { Component, OnInit } from '@angular/core';
import { PayCard, PayCardCreateModel } from '../../response/pay-card';
import { CoreService } from '../../services/core.service';
import { VerifyService } from '../../services/verify.service';
import { SimpleResponse } from '../../response/response';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-pay-card-add',
  templateUrl: './pay-card-add.component.html',
  styleUrls: ['./pay-card-add.component.css']
})
export class PayCardAddComponent implements OnInit {

  model: PayCardCreateModel;
  count = 1;
  countError: string | boolean = '';
  summary = '';
  summaryError: string | boolean = '';
  expiry = 24;
  expiryError = '';
  denominationError: string | boolean = '';
  amountError: string | boolean = '';
  prefix = '';
  perfixError = '';
  customer = '';
  customerPhone = '';
  isSold = false;

  private _denomination = '0.00';
  set denomination(val: string) {
    const moneyReg = /^[1-9]\d*(\.\d{1,2})?$/g;
    if (!moneyReg.test(val)) {
      this.denominationError = 'error';
    } else {
      this.denominationError = '';
      this._denomination = val;
    }
  }
  get denomination() {
    return this._denomination;
  }

  private _amount = '0.00';
  set amount(val: string) {
    const moneyReg = /^[1-9]\d*(\.\d{1,2})?$/g;
    if (!moneyReg.test(val)) {
      this.amountError = 'error';
    } else {
      this.amountError = '';
      this._amount = val;
    }
  }

  get amount() {
    return this._amount;
  }


  constructor(private cs: CoreService, private msg: NzMessageService) { }

  ngOnInit() {

    if (!!this.model) {
      this.count = this.model.count;
      this.summary = this.model.note;
      this.expiry = this.model.expiryMonth;
      this.denomination = this.cs.fomartMoney(this.model.denomination);
      this.amount = this.cs.fomartMoney(this.model.amount);
      this.customer = this.model.customer;
      this.customerPhone = this.model.customerPhone;
    }
  }

  create() {
    const vs = new VerifyService();

    this.countError = vs.gtZero(this.count);
    this.summaryError = vs.isNotEmpty(this.summary);
    this.expiryError = vs.gtZero(this.expiry);
    this.denominationError = vs.isMoney(this.denomination);
    this.amountError = vs.isMoney(this.amount);

    if (vs.isValid) {
      const url = `${this.cs.UrlPrefix}/admin/finance/pay-card`;
      this.cs.post(url, {
        id: 0,
        count: this.count,
        note: this.summary,
        denomination: Number(this.denomination) * 1000,
        amount: Number(this.amount) * 1000,
        expiryMonth: this.expiry,
        customer: this.customer,
        customerPhone: this.customerPhone,
        isSold: this.isSold ? 1 : 0
      }).toPromise().then((x: SimpleResponse<PayCard>) => {
        if (x.status === 0) {
          this.msg.success('已保存。');
        } else {
          this.msg.error('后台有点问题，请稍后再试。');
          console.log('submit pay card:', x);
        }
      });
    }
  }

}
