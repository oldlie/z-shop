import { Component, OnInit, Input } from '@angular/core';
import { Article, ArticleStatus } from '../../../response/article';
import { CoreService } from '../../../services/core.service';
import { NzMessageService, UploadFile } from 'ng-zorro-antd';

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent implements OnInit {

  @Input()
  model: Article;
  uploadUrl: string;
  fileList = [
    {
      uid: -1,
      name: 'xxx.png',
      status: 'done',
      url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png'
    }
  ];
  previewImage = '';
  previewVisible = false;

  constructor(private core: CoreService, private msg: NzMessageService) { }

  ngOnInit() {
    this.uploadUrl = this.core.UrlPrefix + '/upload';

    if (!this.model) {
      this.model = {
        title: '',
        summary: '',
        imageUrl: 'article/default.jpg',
        author: this.core.account,
        content: '',
        status: +ArticleStatus.draft
      };
    }
  }

  handlePreview = (file: UploadFile) => {
    this.previewImage = file.url || file.thumbUrl;
    this.previewVisible = true;
  }

}
