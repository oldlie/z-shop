import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HomeService } from '../../service/home.service';
import { Article } from '../../model/response';
import { NzMessageService } from 'ng-zorro-antd';

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

  constructor(private route: ActivatedRoute, 
    private message: NzMessageService,
    private homeService: HomeService) { }

  ngOnInit() {
    this.articleId = Number(this.route.snapshot.paramMap.get('id'));
    this.homeService.articleDetail(this.articleId).then(x => {
      console.log('article:', x);
      if (x.status === 0) {
        this.article = x.article;
      } else {
        this.message.error(x.message);
      }
    });
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
}
