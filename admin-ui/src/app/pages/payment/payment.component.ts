import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  /**
   * 0: pay card table
   * 1: new pay cards
   */
  viewModel = 0;

  constructor() { }

  ngOnInit() {
  }

  newPayCardsEvent() {
    this.viewModel = 1;
  }
}
