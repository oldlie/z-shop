import { Component, OnInit } from '@angular/core';
import { Carousel, CommodityInfo, Article } from '../../model/response';
import { HomeService } from '../../service/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [
    HomeService
  ]
})
export class HomeComponent implements OnInit {

  array = [1, 2, 3, 4];
  data = new Array(5).fill({}).map((i, index) => {
    return {
      href: 'http://ant.design',
      title: `ant design part ${index}`,
      avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
      description: 'Ant Design, a design language for background applications, is refined by Ant UED Team.',
      content: `We supply a series of design principles, practical patterns and high quality design resources\
(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.`
    };
  });

  carouselItemList: Array<Carousel>;
  commodityInfoList: Array<CommodityInfo>;
  artileList: Array<Article>;

  articleIndex = 0;
  articleSize = 10;

  constructor(private homeService: HomeService) { }

  ngOnInit() {
  }

  initCarousel() {
    this.homeService.initCarousel().then(x => {
      if (x.status === 0) {
        this.carouselItemList = x.list;
      } else {
        console.log(x);
      }
    });
  }

  initCommodity() {
    this.homeService.initCommodity().then(x => {
      if (x.status === 0) {
        this.commodityInfoList = x.list;
      } else {
        console.log(x);
      }
    });
  }

  initArticle() {
    this.homeService.initArticle(this.articleIndex, this.articleSize).then(x => {
      if (x.status === 0) {
        this.artileList = x.list;
      } else {
        console.log(x);
      }
    });
  }
}
