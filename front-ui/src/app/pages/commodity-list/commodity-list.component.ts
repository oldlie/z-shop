import { Component, OnInit } from '@angular/core';
import { CommodityVI } from '../../model/vi';
import { HomeService } from '../../service/home.service';
import { CoreService } from '../../service/core.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-commodity-list',
  templateUrl: './commodity-list.component.html',
  styleUrls: ['./commodity-list.component.css'],
  providers: [
    HomeService
  ]
})
export class CommodityListComponent implements OnInit {

  commodityList = [] as Array<CommodityVI>;
  page = 0;
  size = 24;
  orderBy = 'id';
  order = 0;

  allTags = [];

  constructor(private coreService: CoreService, private homeService: HomeService, private router: Router) { }

  ngOnInit() {
    this.loadData();
    this.homeService.tagAll().then(x => {
      console.log('load tags', x);
      if (x.status === 0) {
        this.allTags = x.tagList;
      } else {
        console.log(x.message);
      }
    });
  }

  loadData() {
    this.homeService.commodityList(this.page, this.size, this.orderBy, this.order)
    .then(x => {
      if (x.status === 0) {
        const temp = [];
        x.list.forEach(e => {
          temp.push({
            id: e.id,
            title: e.title,
            desc: e.summary,
            image: `${this.coreService.Config.resourceURI}/${e.image}`
          });
        });
        this.commodityList = temp;
      } else {
        console.log(x);
      }
    });
  }

  gotoCommodityPage(id: number) {
    this.router.navigate(['/commodity/detail', id]).catch(err => {
      console.log(err);
    });
  }
}
