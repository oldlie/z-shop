import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { AddressRequet } from 'src/app/model/address';
import { FrontService } from 'src/app/service/front.service';
import { NzMessageService } from 'ng-zorro-antd';
import { CoreService } from 'src/app/service/core.service';

@Component({
  selector: 'app-address-add',
  templateUrl: './address-add.component.html',
  styleUrls: ['./address-add.component.css'],
  providers: [
    FrontService
  ]
})
export class AddressAddComponent implements OnInit {

  @Input()
  address: AddressRequet;

  constructor(private cs: CoreService, private fs: FrontService, private ms: NzMessageService) { }

  ngOnInit() {
    if (!this.address) {
      this.address = {
        contactName: '',
        phone: '',
        province: '北京',
        city: '北京',
        county: '朝阳',
        detail: '',
        isDefault: 1
      };
    }
  }

  save() {
    if (this.address.contactName === '' || this.address.phone === '' ||
      this.address.province === '' || this.address.city === '' || this.address.county === '' ||
      this.address.detail === ''
    ) {
      return;
    }
    this.address.userId = this.cs.ProfileInfo.id;
    this.address.isDefault = this.address.isDefault ? 1 : 0;
    this.fs.addAddress(this.address).then(x => {
      if (x.status === 0) {
        this.address.id = x.item;
        this.ms.success('已保存。');
      } else {
        this.ms.error('操作未成功，请稍后再试。');
        console.log(x);
      }
    });
  }
}
