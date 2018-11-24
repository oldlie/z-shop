import { Component, OnInit } from '@angular/core';
import { CoreService } from 'src/app/service/core.service';
import { VerifyService } from 'src/app/service/verify.service';
import { BaseResponse } from 'src/app/model/response';
import { NzMessageService } from 'ng-zorro-antd';
import { UserVI } from 'src/app/model/vi';
import { SimpleResponse } from 'src/app/model/simple.response';

@Component({
  selector: 'app-recharge',
  templateUrl: './recharge.component.html',
  styleUrls: ['./recharge.component.css']
})
export class RechargeComponent implements OnInit {

  number = '';
  numberError = '';
  code = '';
  codeError = '';
  balance = '0.00';

  user: UserVI;

  constructor(private cs: CoreService, private msg: NzMessageService) { }

  ngOnInit() {
    const url = `${this.cs.Config.apiURI}/front/profile/${this.cs.LoginInfo.account}`;
    this.cs.get(url, null).toPromise().then((x: SimpleResponse<UserVI>) => {
      if (x.status === 0) {
        this.user = x.item;
        this.balance = this.cs.fomartMoney(this.user.money);
      } else {
        this.user = {
          username: '',
          userNickname: '',
          cellphone: '',
          cellphone2: '',
          resume: '',
          image: '',
          isInit: 1
        };
        console.error(x);
      }
    });
  }

  recharge() {

    const vs = new VerifyService();

    this.numberError = vs.isNotEmpty(this.number);
    this.codeError = vs.isNotEmpty(this.code);

    if (!vs.isValid) {
      return;
    }

    const url = `${this.cs.Config.apiURI}/front/shopping/recharge`;
    this.cs.post(url, {number: this.number, code: this.code}).toPromise().then((x: SimpleResponse<number>) => {
      if (x.status === 0) {
        this.balance = this.cs.fomartMoney(x.item);
        this.msg.success('充值完成。');
      } else {
        this.msg.error('服务器忙，请稍后再试。');
        console.error(x);
      }
    });
  }
}
