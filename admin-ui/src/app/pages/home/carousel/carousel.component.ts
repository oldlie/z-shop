import { Component, OnInit } from '@angular/core';
import { FileResponse, UploadFile as File } from '../../../response/response';
import { UploadFile, NzMessageService } from 'ng-zorro-antd';
import { CoreService } from '../../../services/core.service';
import { ValidateStatus } from '../../vi';
import { HomeService } from '../../../services/home.service';
import { Carousel } from '../../../response/home.response';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css'],
  providers: [
    HomeService
  ]
})
export class CarouselComponent implements OnInit {

  validateStatus = new ValidateStatus();
  list: Array<Carousel>;
  title = '';
  titleTip = '';
  titleStatus = 'default';
  url = '';
  urlTip = '';
  urlStatus = 'default';
  sequence = 0;
  color = '#ffffff';

  uploadUrl = '';
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
  uploadFileList = [];

  constructor(private core: CoreService, 
    private home: HomeService,
    private msg: NzMessageService) { }

  ngOnInit() {
    this.uploadUrl = this.core.UrlPrefix + '/upload';
    this.loadCarousel();
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

  save() {
    if ('' === this.title) {
      this.titleTip = '请填写标题';
      this.titleStatus = this.validateStatus.warning;
      return;
    } else {
      this.titleTip = '';
      this.titleStatus = this.validateStatus.success;
    }

    if ('' === this.url) {
      this.urlTip = '请填写资源URL';
      this.urlStatus = this.validateStatus.warning;
      return;
    } else {
      this.urlTip = '';
      this.urlStatus = this.validateStatus.success;
    }

    if (this.uploadFileList.length === 0) {
      this.msg.warning('请设置需要播放的图片');
      return;
    }
    const image = this.uploadFileList[0].path;

    this.home
      .addCarousel(null, this.title, this.url, image, this.sequence, this.color)
      .then(x => {
        if (x.status === 0) {
          this.msg.success('已添加');
        } else {
          this.msg.error(x.message);
        }
      });
  }

  loadCarousel() {
    this.home.listCarousel().then(x => {
      if (x.status === 0) {
        this.list = x.list;
      }
    });
  }
}
