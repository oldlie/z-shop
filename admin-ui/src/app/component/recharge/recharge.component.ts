import { Component, OnInit } from '@angular/core';
import { CoreService } from '../../services/core.service';
import { VerifyService } from '../../services/verify.service';

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

  constructor(private cs: CoreService) { }

  ngOnInit() {
  }

  recharge() {

    const vs = new VerifyService();

    this.numberError = vs.isNotEmpty(this.number);
    this.codeError = vs.isNotEmpty(this.code);

    if (!vs.isValid) {
      return;
    }

    const url = `${this.cs.UrlPrefix}/admin/finance/pay-card`;
  }
}
