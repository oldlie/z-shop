import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HomeService } from '../../service/home.service';
import { Article } from '../../model/response';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {

  articleId = 0;
  article: Article;

  constructor(private route: ActivatedRoute, 
    private message: NzMessageService,
    private homeService: HomeService) { }

  ngOnInit() {
    this.articleId = Number(this.route.snapshot.paramMap.get('id'));
    this.homeService.articleDetail(this.articleId).then(x => {
      if (x.status === 0) {
        this.article = x.article;
      } else {
        this.message.error(x.message);
      }
    });
  }

}
