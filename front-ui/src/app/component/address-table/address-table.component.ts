import { Component, OnInit } from '@angular/core';
import { AddressResponse, AddressRequet } from 'src/app/model/address';
import { FrontService } from 'src/app/service/front.service';
import { NzMessageService } from 'ng-zorro-antd';
import { CoreService } from 'src/app/service/core.service';

@Component({
  selector: 'app-address-table',
  templateUrl: './address-table.component.html',
  styleUrls: ['./address-table.component.css'],
  providers: [
    FrontService
  ]
})
export class AddressTableComponent implements OnInit {

  addressList: Array<AddressResponse>;
  currentAddress: AddressRequet;
  viewModel = 0;

  constructor(private cs: CoreService, private fs: FrontService, private ms: NzMessageService) { }

  ngOnInit() {
    this.loadAddressList();
  }

  loadAddressList() {
    const uid = this.cs.ProfileInfo.id;
    this.fs.myAddressList(uid).then(x => {
      if (x.status === 0) {
        this.addressList = x.list;
      } else {
        this.addressList = [];
      }
    });
  }

  gotoCreateView() {
    this.currentAddress = {
      contactName: '',
      phone: '',
      province: '北京',
      city: '北京',
      county: '朝阳',
      detail: '',
      isDefault: 1
    };
    this.viewModel = 1;
  }

  gotoEditView(address: AddressResponse) {
    this.currentAddress = address;
    console.log('gotoEditView', this.currentAddress);
    this.viewModel = 1;
  }

  delete(address: AddressResponse) {
    this.fs.deleteAddress(address.id).then(x => {
      if (x.status === 0) {
        const _temp = this.addressList.filter(item => item.id !== address.id);
        this.addressList = _temp;
      } else {
        this.ms.error('暂时不能删除，请稍后再试。');
        console.error(x);
      }
    });
  }

  gotoListView() {
    this.viewModel = 0;
    this.loadAddressList();
  }

  hendleSendAddressEvent(address: AddressRequet) {
    console.log('----hendleSendAddressEvent----');
    console.log(address);
    if (!!this.currentAddress.id) {
      this.loadAddressList();
    }
  }
}
