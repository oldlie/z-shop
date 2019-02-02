import { Component, OnInit } from '@angular/core';
import { Tag, TagList } from '../../response/tag';
import { TagService } from '../../services/tag.service';
import { NzMessageService } from 'ng-zorro-antd';
import { Base } from '../../response/response';

@Component({
  selector: 'app-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.css'],
  providers: [
    NzMessageService,
    TagService,
  ]
})
export class TagComponent implements OnInit {

  list: Array<ElTag>;
  page = 1;
  pages = 1;
  size = 30;
  total = 0;
  newTagTitle = '';

  constructor(private message: NzMessageService, private tag: TagService) { }

  ngOnInit() {
    this.loadList();
   // this.message.setOptions({ showClose: true, zIndex: 2000 });
  }

  save() {
    console.log('click save button');
    this.newTagTitle = this.newTagTitle.replace(/(^\s|\s$)*/g, '');
    if (this.newTagTitle === '') {
      this.message.create('warning', '请填写标签名字');
      return;
    }
    this.tag.save(this.newTagTitle).then(res => {
      if (res.status === 0) {
        this.message.create('success', `标签[${this.newTagTitle}]已经保存`);
        this.newTagTitle = '';
        this.loadList();
      } else {
        this.message.create('warning', res.message);
      }
    });
  }

  deleteTag(tag: ElTag) {
    this.tag.delete(tag.id).then((res: Base) => {
      if (res.status === 0) {
        this.message.create('success', `标签[${tag.id}:${tag.name}]已经删除。`);
        this.loadList();
      } else {
        this.message.create('warning', res.message);
      }
    });
  }

  private loadList() {
    this.tag.list(this.page, this.size).then((response: TagList) => {
      console.log(response);
      this.list = [];
      if (response.status === 0) {
        for (const item of response.tagList) {
          this.list.push({
            id: item.id,
            name: item.title,
            type: ''
          });
        }
        this.pages = response.pages;
        this.total = response.total;
      } else {
        this.message.create('warning', response.message);
      }
    });
  }
}

interface ElTag {
  id: number;
  name: string;
  type: string;
}
