import { Component, OnInit } from '@angular/core';
import { FileResponse, UploadFile as File } from '../../../response/response';
import { UploadFile } from 'ng-zorro-antd';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {

  list = [1, 2, 3, 4];
  title = '';
  titleTip = '';
  url = '';
  urlTip = '';
  sequence = 0;

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

  constructor() { }

  ngOnInit() {
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

  }
}
