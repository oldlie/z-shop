import { Component, OnInit, Input } from '@angular/core';
import { NavigationVi, NavigationType } from '../navigation-vi';
import { ElNotificationService } from 'element-angular';

@Component({
  selector: 'app-navigation-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  model: NavigationVi;
  @Input()
  viewType: NavigationType;
  showListDialog = false;

  constructor(private notify: ElNotificationService) { }

  ngOnInit() {
    this.model = {
      id: 0,
      title: '',
      parentId: 0
    };
  }

  recevieParent(item: NavigationVi) {
    console.log('Receive Parent Nav:', item);
    this.model.parentId = item.id;
    this.showListDialog = false;
    console.log(this.model);
  }

  asignParent() {
    this.showListDialog = true;
  }

  save(event: Event) {
    if (this.model.title === '') {
      this.notify.warning('请填写导航名称');
      return;
    }
  }
}
