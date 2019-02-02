import { Component, OnInit, Input } from '@angular/core';
import { Article, ArticleStatus, ArticleMenu } from '../../../response/article.response';
import { CoreService } from '../../../services/core.service';
import { NzMessageService, UploadFile } from 'ng-zorro-antd';
import { ArticleService } from '../../../services/article.service';
import { TagService } from '../../../services/tag.service';
import { Tag } from '../../../response/tag';
import { ArticleVI } from '../article.vi';
import { FileResponse, UploadFile as File } from '../../../response/response';

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css'],
  providers: [
    ArticleService,
    TagService,
  ]
})
export class AddArticleComponent implements OnInit {

  @Input() editArticle: Article;
  model: ArticleVI;
  uploadUrl: string;
  fileList = [];
  previewImage = '';
  previewVisible = false;
  uploadFileList = new Array<File>();

  refreshMenu = false;
  menuList = [];
  menuTempList = [];
  menuCheckedList = [];
  tagList = new Array<TagVI>();
  tagTempList = new Array<Tag>();
  tagCheckedList = new Array<Tag>();
  saveAsDraft = false;

  isMenuVisible = false;
  menuLoading = false;
  isTagVisible = false;
  tagPage = 1;
  tagSize = 20;
  tagPages = 2;
  flag = true;

  options: Object = {};

  constructor(private article: ArticleService,
    private core: CoreService,
    private msg: NzMessageService,
    private tag: TagService
  ) { }

  ngOnInit() {
    this.uploadUrl = this.core.UrlPrefix + '/upload';
    this.options = {
      placeholder: '写文章',
      imageUploadMethod: 'POST',
      imageUploadURL: this.core.UrlPrefix + '/image',
    };
    console.log(this.editArticle);
    if (this.editArticle) {
      console.log('inited');
      this.model = {
        id: this.editArticle.id,
        title: this.editArticle.title,
        summary: this.editArticle.summary,
        imageUrl: this.editArticle.imageUrl,
        author: this.editArticle.author,
        content: this.editArticle.content,
        status: this.editArticle.status
      };
      this.saveAsDraft = this.editArticle.status === 0;
      console.log('menus', this.editArticle.menus);
      this.refreshMenu = true;
      this.menuCheckedList = this.editArticle.menus;
      console.log(this.menuCheckedList);
      this.tagCheckedList = this.editArticle.tags;
    } else {
      console.log('no init');
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

  uploadChange(change: any) {
    if (change['type'] === 'success') {
      const response = change['file']['response'] as FileResponse;
      console.log(response);
      this.uploadFileList = response.list;
    }
  }

  handlePreview = (file: UploadFile) => {
    this.previewImage = file.url || file.thumbUrl;
    this.previewVisible = true;
  }

  addMenu() {
    this.isMenuVisible = true;
    this.menuLoading = true;
    this.menuTempList = [];
    this.refreshMenu = false;
    this.article.childrenMenu(0).then(x => {
      this.menuLoading = false;
      if (x.status === 0) {
        this.menuList = x.list;
      }
    });
  }

  handleMenuCancel() {
    this.isMenuVisible = false;
  }

  handleMenuOK() {
    this.isMenuVisible = false;
  }

  cc(menu: ArticleMenu) {
    this.isMenuVisible = false;
    this.menuCheckedList = this.menuTempList;
    this.menuCheckedList.push(menu);
    this.refreshMenu = true;
  }

  ccc(menu: ArticleMenu) {
    this.menuTempList.push(menu);
    this.menuLoading = true;
    this.article.childrenMenu(menu.id).then(res => {
      this.menuLoading = false;
      if (res.status === 0) {
        this.menuList = res.list;
      } else {
        this.msg.warning(res.message);
      }
    });
  }

  addTag() {
    this.isTagVisible = true;
    this.tagPage = 0;
    this.loadTag();
  }

  nextTagPage() {
    if (this.tagPage < this.tagPages - 1) {
      this.tagPage++;
      this.loadTag();
    }
  }

  prevTagPage() {
    if (this.tagPage > 0) {
      this.tagPage--;
      this.loadTag();
    }
  }

  checkTag(tag: TagVI) {
    tag.isChecked = tag.isChecked ? false : true;
  }

  checkedTag() {
    for (const tag of this.tagList) {
      if (tag.isChecked) {
        this.tagCheckedList.push(tag.tag);
      }
    }
    this.isTagVisible = false;
  }

  deleteTag(tag: Tag) {
    const dataSet = this.tagCheckedList.filter(d => d.id !== tag.id);
    this.tagCheckedList = dataSet;
  }

  private loadTag() {
    this.tag.list(this.tagPage, this.tagSize).then(x => {
      this.tagList = [];
      if (x.status === 0) {
        for (const tag of x.tagList) {
          this.tagList.push({
            isChecked: false,
            tag: tag
          });
        }
        this.tagPages = x.pages;
      }
    });
  }

  handleTagCancel() {
    this.isTagVisible = false;
  }

  store() {
    if (!this.model.title || '' === this.model.title) {
      this.msg.warning('请填写标题');
      return;
    }

    if (this.uploadFileList.length === 1) {
      this.model.imageUrl = this.uploadFileList[0].path;
    }

    if (this.menuCheckedList.length > 0) {
      const menuIds = [];
      this.menuCheckedList.forEach(x => {
        menuIds.push(x.id);
      });
      this.model.menus = menuIds.join(',');
    } else {
      this.model.menus = '';
    }

    if (this.tagCheckedList.length > 0) {
      const tagIds = [];
      this.tagCheckedList.forEach(x => {
        tagIds.push(x.id);
      });
      this.model.tags = tagIds.join(',');
    } else {
      this.model.tags = '';
    }

    this.model.status = this.saveAsDraft ? 0 : 1;
    this.model.author = this.core.account;

    console.log('save:', this.model);

    this.article.save(this.model).then(x => {
      if (x.status === 0) {
        this.msg.success('已保存');
      } else {
        this.msg.error(x.message);
      }
    });

  }
}

interface TagVI {
  tag: Tag;
  isChecked: boolean;
}
