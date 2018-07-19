import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Commodity, CommodityInfo, Specification } from '../../model/response';
import { HomeService } from '../../service/home.service';
import { NzMessageService } from 'ng-zorro-antd';
import { CoreService } from '../../service/core.service';

@Component({
  selector: 'app-commodity',
  templateUrl: './commodity.component.html',
  styleUrls: ['./commodity.component.css'],
  providers: [
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
    private route: ActivatedRoute,
    private homeService: HomeService,
    private message: NzMessageService
  ) { }

  ngOnInit() {
    this.commodityId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadCommodity(this.commodityId);

  }

  checkSpec(specification: Specification) {
    this.specification = specification;
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
