import { Component, OnInit, Input } from '@angular/core';
import { CommoditySpecVI } from '../../commodity-vi';
import { NzMessageService } from 'ng-zorro-antd';
import { CommodityService } from '../../../../services/commodity.service';

@Component({
  selector: 'app-commodity-spec-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css'],
  providers: [
    CommodityService,
  ]
})
export class AddComponent implements OnInit {

  @Input()
  spec: CommoditySpecVI;

  constructor(private commodity: CommodityService,
     private message: NzMessageService) { }

  ngOnInit() {

    console.log('init?', this.spec);
    if (!this.spec) {
      this.spec = {
        title: '',
        spec: '',
        price: 1,
        inventory: 0
      };
    }

  }

  save() {
    console.log('save', this.spec.types);

    if (this.spec.title === '') {
      this.message.create('warning', '请填写规格名称。');
      return;
    }

    if (this.spec.spec === '') {
      this.message.create('warning', '请填写具体规格。');
      return;
    }

    if (this.spec.price <= 0) {
      this.message.create('warning', '请填写价格。');
      return;
    }

    if (this.spec.inventory <= 0) {
      this.message.create('warning', '请填写库存。');
      return;
    }

    this.commodity.saveSpec(this.spec).then(res => {
      if (res.status === 0) {
        this.message.create('success', '规格已经保存了。');
      } else {
        this.message.create('warning', res.message);
      }
    });
  }
}
