import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Commodity, CommodityInfo, Specification } from '../../model/response';
import { HomeService } from '../../service/home.service';
import { NzMessageService, NzNotificationService } from 'ng-zorro-antd';
import { CoreService } from '../../service/core.service';
import { FrontService } from '../../service/front.service';
import { ShoppingCartRequest } from '../../model/shopping-cart';

@Component({
  selector: 'app-commodity',
  templateUrl: './commodity.component.html',
  styleUrls: ['./commodity.component.css'],
  providers: [
    FrontService,
    HomeService
  ]
})
export class CommodityComponent implements OnInit {

  array = [ 1, 2, 3, 4 ];
  effect = 'scrollx';
  specId = 0;
  specification: Specification = {
    title: ''
  };
  content = '';
  count = 1;
  price = 0;

  commodityId = 0;
  images: Array<string>;
  commodityInfo: CommodityInfo = {
    commodity: {
      id: 0,
      title: '',
      summary: '',
      description: '',
      status: 1,
      ranking: 0,
      rankingCount: 0,
      viewCount: 0,
      shareCount: 0
    }
  };

  constructor(private core: CoreService,
    private fs: FrontService,
    private route: ActivatedRoute,
    private homeService: HomeService,
    private message: NzMessageService,
    private notify: NzNotificationService
  ) { }

  ngOnInit() {
    this.commodityId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadCommodity(this.commodityId);
  }

  checkSpec(specification: Specification) {
    this.specification = specification;
  }

  add2ShoppingCart() {
    console.log('specification:', this.specification);
    console.log('profile:', this.core.ProfileInfo);
    const request = {} as ShoppingCartRequest;
    request.commodityId = this.commodityId;
    request.uid = this.core.ProfileInfo.id;
    request.specId = this.specification.id;
    request.count = this.count;
    request.price = this.specification.price;
    request.id = 0;
    console.log('add to shopping cart request:', request);
    this.fs.addCommodity2ShoppingCart(request).then(x => {
      if (x.status === 0) {
        this.notify.success('OK', '已添加到购物车');
      } else {
        console.error('add 2 shopping cart failed:', x);
      }
    });
  }

  private loadCommodity(id: number) {
    this.homeService.commodityDetail(this.commodityId).then(x => {
      console.log(x);
      if (x.status === 0) {
        this.commodityInfo = x.commodityInfo;
        if (this.commodityInfo.commodity.specifications &&
           this.commodityInfo.commodity.specifications.length > 0) {
             this.specification = this.commodityInfo.commodity.specifications[0];
             console.log(this.specification);
           }
        this.content = this.commodityInfo.commodity.description.replace(/\<img/g, '<img class="img-responsive"');
        const list = [];
        for (const image of this.commodityInfo.images) {
          list.push(this.core.Config.resourceURI + '/'
            + image.imagePath.replace(/\\/g, '/'));
        }
        this.images = list;
        console.log('images', this.images);
      } else {
        this.message.error(x.message);
      }
    });
  }
}
