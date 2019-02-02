import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ArticleService } from '../../../services/article.service';
import { Article } from '../../../response/article.response';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-list-article',
  templateUrl: './list-article.component.html',
  styleUrls: ['./list-article.component.css'],
  providers: [
    ArticleService
  ]
})
export class ListArticleComponent implements OnInit {

  list = new Array<Article>();
  page = 0;
  size = 10;
  orderBy = 'id';
  order = 1;
  @Output() editArticeEvent = new EventEmitter<Article>();

  constructor(private article: ArticleService, private msg: NzMessageService) { }

  ngOnInit() {
    this.loadArticle();
  }

  private loadArticle() {
    this.article.load(this.page, this.size, this.orderBy, this.order).then(x => {
      if (x.status === 0) {
        this.list = x.list;
      } else {
        this.msg.error(x.message);
      }
    });
  }

  delete(item: Article) {
    this.article.delete(item.id).then(x => {
      if (x.status === 0) {
        const temp = this.list.filter(y => y.id !== item.id);
        this.list = temp;
        this.msg.success('已删除');
      } else {
        this.msg.error(x.message);
      }
    });
  }

  edit(item: Article) {
    this.editArticeEvent.emit(item);
  }

  add2Home(item: Article) {
    this.article.add2Home(item.id).then(x => {
      if (x.status === 0) {
        this.msg.success('已置顶');
      } else {
        this.msg.error(x.message);
      }
    });
  }
}
