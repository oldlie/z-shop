import { Component, OnInit } from '@angular/core';
import { CommoditySpecVI } from '../../commodity-vi';
import { ElNotificationService } from 'element-angular';

@Component({
  selector: 'app-commodity-spec-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  spec: CommoditySpecVI;

  constructor(private notify: ElNotificationService) { }

  ngOnInit() {
    this.spec = {
      title: '',
      spec: '',
      price: 1,
      inventory: 0
    };
  }

  onNotify(type: string) {
    this.notify[type]('这是一条消息提示: ' + type, type);
  }

}
