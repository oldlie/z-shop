import { Component, OnInit } from '@angular/core';
import { Commodity, CommodityInfo } from '../../../response/commodity.response';
import { CommodityService } from '../../../services/commodity.service';
import { HomeService } from '../../../services/home.service';
import { CoreService } from '../../../services/core.service';
import { NzNotificationService } from 'ng-zorro-antd';

@Component({
  selector: 'app-home-commodity',
  templateUrl: './commodity.component.html',
  styleUrls: ['./commodity.component.css'],
  providers: [
    CommodityService,
    HomeService
  ]
})
export class CommodityComponent implements OnInit {

  list = new Array<HomeCommodityInfoVI>();

  constructor(private core: CoreService, private home: HomeService, private notify: NzNotificationService) { }

  ngOnInit() {
    this.loadCommodity();
  }

  loadCommodity() {
    this.home.listHomeCommodity().then(x => {
      if (x.status === 0) {
        console.log(x);
        if (x.list && x.list.length > 0) {
          const temp = new Array<HomeCommodityInfoVI>();
          for (const item of x.list) {
            const image = item.images[0].imagePath.replace(/\\/g, '/');
            temp.push({
              id: item.commodity.id,
              title: item.commodity.title,
              desc: item.commodity.summary,
              image: `${this.core.ResourceURI}/${image}`
            });
          }
          this.list = temp;
        }
      }
    });
  }

  cancelShowOnHomePage(item: HomeCommodityInfoVI) {
    this.home.deleteHomeCommodity(item.id).then(x => {
      if (x.status === 0) {
        const temp = this.list.filter(y => y.id !== item.id);
        this.list = temp;
        this.notify.success('成功', '已经从首页取消。');
      } else {
        this.notify.error('注意', x.message);
      }
    });
  }
}

export interface HomeCommodityInfoVI {
  id: number;
  title: string;
  desc: string;
  image: string;
}
