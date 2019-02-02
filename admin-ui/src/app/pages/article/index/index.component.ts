import { Component, OnInit } from '@angular/core';
import { Article } from '../../../response/article.response';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  index = 0;
  tabTitle = '新建';
  editArticle = null;

  constructor() { }

  ngOnInit() {
  }

  handle(index: number) {
    if (this.index === 1) {
      this.editArticle = null;
    }
    this.index = index;
  }

  onEditArticleEvent(item: Article) {
    this.editArticle = item;
    this.index = 1;
  }
}
