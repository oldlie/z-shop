import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HomeService } from '../../service/home.service';
import { Article } from '../../model/response';
import { NzMessageService } from 'ng-zorro-antd';
import { CommodityVI } from '../../model/vi';
import { CoreService } from '../../service/core.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css'],
  providers: [
    HomeService
  ]
})
export class ArticleComponent implements OnInit {

  articleId = 0;
  article: Article = {
    title: '',
    summary: '',
    author: '',
    imageUrl: '',
    content: '',
    status: 1,
    tags: []
  };
  content = '';
  allTags = [];
  commodityList: Array<CommodityVI>;
  effect = 'scrollx';

  constructor(private coreService: CoreService,
    private router: Router,
    private route: ActivatedRoute,
    private message: NzMessageService,
    private homeService: HomeService) { }

  ngOnInit() {
    this.articleId = Number(this.route.snapshot.paramMap.get('id'));
    this.homeService.articleDetail(this.articleId).then(x => {
      console.log('article:', x);
      if (x.status === 0) {
        this.article = x.article;
        this.content = this.article.content.replace(/\<img/g, '<img class="img-responsive"');
      } else {
        this.message.error(x.message);
      }
    });
    this.homeService.tagAll().then(x => {
      console.log('load tags', x);
      if (x.status === 0) {
        this.allTags = x.tagList;
      } else {
        console.log(x.message);
      }
    });
    this.initCommodity();
  }

  agree(id: number) {
    this.homeService.articleAgree(id).then(x => {
      if (x.status === 0) {
        this.article.agreeCount = x.count;
      } else {
        this.message.error(x.message);
      }
    });
  }

  initCommodity() {
    this.homeService.initCommodity().then(x => {
      if (x.status === 0) {
        const temp = [] as Array<CommodityVI>;
        for (const item of x.list) {
          const image = item.image;
          temp.push({
            id: item.id,
            title: item.title,
            desc: item.summary,
            image: `${this.coreService.Config.resourceURI}/${image}`
          });
        }
        this.commodityList = temp;
      } else {
        console.log(x);
      }
    });
  }

  gotoCommodityPage(id: number) {
    this.router.navigate(['commodity', id]).catch(err => {
      console.log(err);
    });
  }
}
