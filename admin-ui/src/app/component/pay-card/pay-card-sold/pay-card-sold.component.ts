import { Component, OnInit, Input } from '@angular/core';
import { PayCard } from '../../../response/pay-card';
import { CoreService } from '../../../services/core.service';
import { NzMessageService } from 'ng-zorro-antd';
import { VerifyService } from '../../../services/verify.service';
import { Base } from '../../../response/response';

@Component({
  selector: 'app-pay-card-sold',
  templateUrl: './pay-card-sold.component.html',
  styleUrls: ['./pay-card-sold.component.css']
})
export class PayCardSoldComponent implements OnInit {

  note = '';
  noteError = '';
  customer = '';
  customerError = '';
  customerPhone = '';
  customerPhoneError = '';
  denomination = '0.00';
  amountError = '';

  @Input()
  item: PayCard;

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
    console.log('pay card sold:', this.item);
    if (!!this.item) {
      this.note = this.item.note;
      this.denomination = this.cs.fomartMoney(this.item.denomination);
      this.amount = this.cs.fomartMoney(this.item.amount);
      this.customer = this.item.customer;
      this.customerPhone = this.item.customerPhone;
    } else {
      this.msg.error('Item is Empty!!!');
    }
  }

  submit() {
    const vs = new VerifyService();
    this.noteError = vs.isNotEmpty(this.note);
    this.amountError = vs.isMoney(this.amount);
    this.customerError = vs.isNotEmpty(this.customer);
    this.customerPhoneError = vs.isNotEmpty(this.customerPhone);

    if (!vs.isValid) {
      return;
    }

    const url = `${this.cs.UrlPrefix}/admin/finance/pay-card`;
    this.item.note = this.note;
    this.item.amount = Number(this.amount) * 1000;
    this.item.customer = this.customer;
    this.item.customerPhone = this.customerPhone;
    this.item.isSoldOut = 1;

    this.cs.post(url, this.item).toPromise().then((x: Base) => {
      if (x.status === 0) {
        this.msg.success('已售出！');
      } else {
        this.msg.error('出了点问题，请稍后再试。');
        console.error(x);
      }
    });
  }
}
