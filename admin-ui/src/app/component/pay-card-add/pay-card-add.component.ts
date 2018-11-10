import { Component, OnInit } from '@angular/core';
import { PayCard, PayCardCreateModel } from '../../response/pay-card';
import { CoreService } from '../../services/core.service';

@Component({
  selector: 'app-pay-card-add',
  templateUrl: './pay-card-add.component.html',
  styleUrls: ['./pay-card-add.component.css']
})
export class PayCardAddComponent implements OnInit {

  model: PayCardCreateModel;
  count = 1;
  countError = false;
  summary = '';
  summaryError = false;
  expiry = 24;
  expiryError = false;
  denomination = '0.00';
  denominationError = false;
  amount = '0.00';
  amountError = false;
  customer = '';
  customerPhone = '';

  constructor(private cs: CoreService) { }

  ngOnInit() {

    if (!this.model) {
      this.model = {
          count: 1,
          note: '',
          denomination: 1000,
          expiryMonth: 24,
          amount: 1000,
          customer: '',
          customerPhone: ''
      };
    }
  }

}
