import { Component, OnInit } from '@angular/core';
import { CommoditySpecVI } from '../../commodity-vi';
import { ElNotificationService } from 'element-angular';
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

  spec: CommoditySpecVI;

  constructor(private commodity: CommodityService,
     private notify: ElNotificationService) { }

  ngOnInit() {
    this.spec = {
      title: '',
      spec: '',
      price: 1,
      inventory: 0
    };
  }

  save() {
    console.log(this.spec.title);
    if (this.spec.title === '') {
      this.notify.warning('请填写规格名称。');
      return;
    }

    if (this.spec.spec === '') {
      this.notify.warning('请填写具体规格。');
      return;
    }

    if (this.spec.price <= 0) {
      this.notify.warning('请填写价格。');
      return;
    }

    if (this.spec.inventory <= 0) {
      this.notify.warning('请填写库存。');
      return;
    }
  }
}
