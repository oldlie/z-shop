import { Component, OnInit } from '@angular/core';
import { Carousel, CommodityInfo, Article } from '../../model/response';
import { HomeService } from '../../service/home.service';
import { CarouselVI, CommodityVI } from '../../model/vi';
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

  carouselItemList: Array<CarouselVI>;
  commodityList: Array<CommodityVI>;
  artileList: Array<Article>;

  articleIndex = 0;
  articleSize = 10;

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
        this.artileList = x.list;
      } else {
        console.log(x);
      }
    });
  }
}
