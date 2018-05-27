import { Component, OnInit, Input } from '@angular/core';
import { CommodityStatus, CommodityVI, CommoditySpecVI } from '../commodity-vi';
import { NzMessageService } from 'ng-zorro-antd';
import { CommoditySpec, CommodityMenu, Commodity } from '../../../response/commodity';
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
  commodityVI: Commodity;

  id = 0;
  title = '';
  summary = '';
  comment = '';
  description = '';
  status = CommodityStatus.init;
  showSpecDialog = false;
  showMenuDialog = false;
  menuLoading = false;
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
        shareCount: 0,
        tags: [],
        menus: [],
        specifications: [],
      };
    } else {
      this.specificationList = this.commodityVI.specifications;
      this.menuList = this.commodityVI.menus;
      this.tagCheckedList = this.commodityVI.tags;
      this.refreshMenu = true;
    }
  }

  addSpec() {
    this.showSpecDialog = true;
    this.specifciationFlag = 1;
  }

  checkSpecListener(specification: CommoditySpecVI) {
    // 我勒个去，阿里这个数据更新实现也太坑了些。
    // 虽然在大数据量的情况下可能会有性能优势，
    // 小数据量简直就是自讨没趣啊
    const dataSet = [];
    dataSet.push(specification);
    this.specificationList.forEach(item => {
      dataSet.push(item);
    });

    this.specificationList = dataSet;
    this.showSpecDialog = false;
  }

  deleteSpec(specification: CommoditySpecVI) {
    this.showSpecDialog = true;

    const dataSet = this.specificationList.filter(d => d.id !== specification.id);
    this.specificationList = dataSet;

    this.showSpecDialog = false;
  }

  addMenu() {
    this.refreshMenu = false;
    this.menuList = [];
    this.menuLoading = true;
    this.commodity.findMenuByParentId(0).then(res => {
      this.menuLoading = false;
      if (res.status === 0) {
        this.menuSourceList = [];
        this.menuSourceList = res.list;
        this.showMenuDialog = true;
      } else {
        console.log(res.message);
      }
    });
  }

  cc(data: CommodityMenu) {
    this.menuList.push(data);
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

  save() {
    if (this.commodityVI.title === '') {
      this.message.warning('请填写标题');
      return;
    }

    if (this.commodityVI.summary === '') {
      this.message.warning('请填写简要描述');
      return;
    }

    if (this.commodityVI.description === '') {
      this.message.warning('请填写详情');
      return;
    }

    if (this.specificationList.length === 0) {
      this.message.warning('至少选择一个规格');
      return;
    }

    if (this.menuList.length === 0) {
      this.message.warning('至少选择一个栏目');
      return;
    }

    this.commodity.save(this.commodityVI, this.menuList, this.tagCheckedList, this.specificationList).then(res => {
      if (res.status === 0) {
        this.message.success('已保存');
      } else {
        this.message.warning(res.message);
      }
    });
  }
}
