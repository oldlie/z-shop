import { Component, OnInit } from '@angular/core';
import { HomeService } from '../../service/home.service';
import { CarouselVI, CommodityVI, ArticleVI } from '../../model/vi';
import { CoreService } from '../../service/core.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [
    HomeService
  ]
})
export class HomeComponent implements OnInit {

  carouselItemList: Array<CarouselVI>;
  commodityList: Array<CommodityVI>;
  artileList: Array<ArticleVI>;

  articleIndex = 0;
  articleSize = 10;
  articleTotal = 1;

  constructor(private coreService: CoreService,
    private homeService: HomeService) { }

  ngOnInit() {
    this.initCarousel();
    this.initCommodity();
    this.initArticle();
  }

  initCarousel() {
    this.homeService.initCarousel().then(x => {
      if (x.status === 0) {
        const temp = new Array<CarouselVI>();
        for (const item of x.list) {
          const url = item.image.replace(/\\/g, '/');
          temp.push({
            title: item.title,
            image: `${this.coreService.Config.resourceURI}/${url}`,
            url: item.url
          });
        }
        this.carouselItemList = temp;
      } else {
        console.log(x);
      }
    });
  }

  initCommodity() {
    this.homeService.initCommodity().then(x => {
      if (x.status === 0) {
        const temp = [] as Array<CommodityVI>;
        for (const item of x.list) {
          const image = item.images[0].imagePath.replace(/\\/g, '/');
          temp.push({
            id: item.commodity.id,
            title: item.commodity.title,
            desc: item.commodity.description,
            image: `${this.coreService.Config.resourceURI}/${image}`,
            info: item
          });
        }
        this.commodityList = temp;
      } else {
        console.log(x);
      }
    });
  }

  initArticle() {
    this.homeService.initArticle(this.articleIndex, this.articleSize).then(x => {
      console.log(x);
      if (x.status === 0) {
        const temp = [] as Array<ArticleVI>;
        this.articleTotal = x.total;
        for (const item of x.list) {
          const image = item.imageUrl ?
            item.imageUrl.replace(/\\/g, '/') : '';
            console.log(image);
          temp.push({
            id: item.id,
            title: item.title,
            summary: item.summary,
            viewCount: item.viewCount,
            agreeCount: item.agreeCount,
            image: `${this.coreService.Config.resourceURI}/${image}`,
            commentCount: 0
          });
        }
        this.artileList = temp;
      } else {
        console.log(x);
      }
    });
  }
}
