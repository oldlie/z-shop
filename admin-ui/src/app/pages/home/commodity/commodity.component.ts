import { Component, OnInit } from '@angular/core';
import { Commodity } from '../../../response/commodity.response';
import { CommodityService } from '../../../services/commodity.service';
import { HomeService } from '../../../services/home.service';

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

  list = new Array<Commodity>();

  constructor(private commodity: CommodityService, private home: HomeService) { }

  ngOnInit() {
  }

  loadCommodity() {
    this.home.listHomeCommodity().then(x => {
      if (x.status === 0) {
        if (x.list && x.list.length > 0) {
          this.list = x.list;
        }
      }
    });
  }
}
