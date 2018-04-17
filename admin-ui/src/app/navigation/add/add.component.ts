import { Component, OnInit, Input } from '@angular/core';
import { NavigationVi, NavigationType } from '../navigation-vi';

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

  constructor() { }

  ngOnInit() {
    this.model = {
      id: 0,
      title: '',
      parentId: 0
    };
  }

  recevieParent(item: NavigationVi) {
    console.log('Receive Parent Nav:', item);
  }

  asignParent() {
    this.showListDialog = true;
  }
}
