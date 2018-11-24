import { Component, OnInit } from '@angular/core';
import { UserVI } from 'src/app/model/vi';
import { CoreService } from 'src/app/service/core.service';
import { SimpleResponse } from 'src/app/model/simple.response';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  user: UserVI = {
    username: '',
    userNickname: '',
    cellphone: '',
    cellphone2: '',
    resume: '',
    image: '',
    isInit: 1
  };
  balance = '';

  constructor(private cs: CoreService) { }

  ngOnInit() {
    const url = `${this.cs.Config.apiURI}/front/profile`;
    this.cs.get(url, {account: this.cs.LoginInfo.account}).toPromise().then((x: SimpleResponse<UserVI>) => {
      if (x.status === 0) {
        this.user = x.item;
        this.balance = this.cs.fomartMoney(this.user.money);
      } else {
        console.error(x);
      }
    });
  }

}
