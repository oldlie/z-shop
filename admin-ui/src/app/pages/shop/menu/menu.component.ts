import { Component, OnInit } from '@angular/core';
import { CommodityService } from '../../../services/commodity.service';
import { ElMessageService } from 'element-angular';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  providers: [
    CommodityService,
  ]
})
export class MenuComponent implements OnInit {

  toggle = false;
  menu = '';
  parentId = 0;
  parent = '根目录';
  comment = '';

  constructor(private commodity: CommodityService, private message: ElMessageService) { }

  ngOnInit() {
  }

  selectParent() {
    this.toggle = true;
    console.log('toggle', this.toggle);
  }

  save() {
    this.commodity.saveMenu(this.menu, this.parentId, this.comment).then(res => {
      if (res.status === 0) {
        this.message.success(`[${this.menu}]已经保存`);
      } else {
        this.message.warning(res.message);
      }
    });
  }
}
