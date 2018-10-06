import { Component, OnInit } from '@angular/core';
import { AddressResponse } from '../../model/address.response';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  addressList = [] as Array<AddressResponse>;
  viewModel = 0;
  addree: AddressResponse;

  constructor() { }

  ngOnInit() {
  }

}
