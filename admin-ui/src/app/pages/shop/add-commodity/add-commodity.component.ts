import { Component, OnInit, Input } from '@angular/core';
import { CommodityStatus, CommodityVI, CommoditySpecVI } from '../commodity-vi';
import { NzMessageService } from 'ng-zorro-antd';
import { CommoditySpec, CommodityMenu } from '../../../response/commodity';
import { Tag, TagList } from '../../../response/tag';
import { CommodityService } from '../../../services/commodity.service';
import { TagService } from '../../../services/tag.service';

@Component({
  selector: 'app-add-commodity',
  templateUrl: './add-commodity.component.html',
  styleUrls: ['./add-commodity.component.css'],
  providers: [
    CommodityService,
    TagService,
  ]
})
export class AddCommodityComponent implements OnInit {

  @Input()
  commodityVI: CommodityVI;

  id = 0;
  title = '';
  summary = '';
  comment = '';
  description = '';
  status = CommodityStatus.init;
  showSpecDialog = false;
  showMenuDialog = false;
  showTagDialog = false;

  specifciationFlag = 1;
  specificationList = new Array<CommoditySpecVI>();
  tagList = new Array<Tag>();
  menuList = new Array<CommodityMenu>();
  menuSourceList = new Array<CommodityMenu>();
  menuParentId = 0;
  refreshMenu = false;
  tagTitle = '';
  tagPage = 0;
  tagPageSize = 20;
  tagPages = 0;
  tagCheckedList = new Array<Tag>();

  constructor(private commodity: CommodityService,
    private message: NzMessageService,
    private tag: TagService) { }

  ngOnInit() {
    if (!this.commodityVI) {
      this.commodityVI = {
        id: 0,
        title: '',
        summary: '',
        comment: '',
        description: '',
        status: 0,
        ranking: 0,
        rankingCount: 0,
        viewCount: 0,
        shareCount: 0
      };
    }
  }

  addSpec() {
    this.showSpecDialog = true;
    this.specifciationFlag = 1;
  }

  checkSpecListener(specification: CommoditySpecVI) {
    this.specificationList.push(specification);
    this.showSpecDialog = false;
  }

  deleteSpec(specification: CommoditySpecVI) {
    console.log('deleteSpec', this.specificationList);
    for (let i = 0; i < this.specificationList.length; i++) {
      const item = this.specificationList[i];

      if (specification.id === item.id) {
        this.specificationList.splice(i, 1);
        return;
      }
    }
  }

  addMenu() {
    this.refreshMenu = false;
    this.menuList = [];
    this.commodity.findMenuByParentId(0).then(res => {
      if (res.status === 0) {
        this.menuSourceList = [];
        this.menuSourceList = res.list;
        console.log('addMenu', res);
        this.showMenuDialog = true;
      } else {
        console.log(res.message);
      }
    });
  }

  cc(data: CommodityMenu) {
    this.menuList.push(data);
    console.log(this.menuList);
    this.showMenuDialog = false;
    this.refreshMenu = true;
  }

  ccc(data: CommodityMenu) {
    this.menuList.push(data);
    this.commodity.findMenuByParentId(data.id).then(res => {
      if (res.status === 0) {
        this.menuSourceList = [];
        this.menuSourceList = res.list;
      } else {
        console.log(res.message);
      }
    });
  }

  checkMenuRoot() {
    this.commodity.findMenuByParentId(0).then(res => {
      if (res.status === 0) {
        this.menuList = [];
        this.menuSourceList = [];
        this.menuSourceList = res.list;
      } else {
        console.log(res.message);
      }
    });
  }

  checkTag(tag: Tag) {
    this.tagCheckedList.push(tag);
  }

  addTag() {
    this.tag.list(this.tagPage, this.tagPageSize).then((response: TagList) => {
      this.tagList = [];
      if (response.status === 0) {
        for (const item of response.tagList) {
          this.tagList.push({
            id: item.id,
            title: item.title,
            parentId: item.parentId
          });
        }
        this.tagPages = response.pages;
        this.showTagDialog = true;
      } else {
        this.message.warning(response.message);
      }
    });
  }

  deleteTag(tag: Tag) {
    for (let i = 0; i < this.tagList.length; i++) {
      if (tag.id === this.tagList[i].id) {
        this.tagCheckedList.splice(i, 1);
        return;
      }
    }
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

  private loadTag() {
    this.tag.list(this.tagPage, this.tagPageSize).then((response: TagList) => {
      if (response.status === 0) {
        this.tagList = [];
        for (const item of response.tagList) {
          this.tagList.push({
            id: item.id,
            title: item.title,
            parentId: item.parentId
          });
        }
        this.tagPages = response.pages;
      }
    });
  }
}
