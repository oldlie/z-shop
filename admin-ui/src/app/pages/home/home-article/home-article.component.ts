import { Component, OnInit } from '@angular/core';
import { HomeService } from '../../../services/home.service';
import { NzMessageService } from 'ng-zorro-antd';
import { Article } from '../../../response/article.response';

@Component({
  selector: 'app-home-article',
  templateUrl: './home-article.component.html',
  styleUrls: ['./home-article.component.css'],
  providers: [
    HomeService
  ]
})
export class HomeArticleComponent implements OnInit {

  articleList = [] as Array<Article>;

  constructor(private homeService: HomeService,
    private msgService: NzMessageService) { }

  ngOnInit() {
    this.homeService.listHomeArticle().then(x => {
      if (x.status === 0) {
        this.articleList = x.list;
        console.log(x);
      } else {
        this.msgService.error(x.message);
      }
    });
  }

  cancle(id: number) {
    console.log(id);
    this.homeService.cancelHomeArticle(id).then(x => {
      if (x.status === 0) {
        this.msgService.success('已取消');
        const list = this.articleList.filter(y => y.id === id);
        this.articleList = list;
      } else {
        this.msgService.error(x.message);
      }
    });
  }
}
