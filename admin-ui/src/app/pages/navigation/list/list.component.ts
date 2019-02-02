import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NavigationVi, NavigationType } from '../navigation-vi';

@Component({
  selector: 'app-navigation-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  @Input()
  modelId = 0;
  @Input()
  viewType: NavigationType;
  @Output()
  choseTrigger = new EventEmitter<NavigationVi>();

  list: Array<NavigationVi>;

  constructor() { }

  ngOnInit() {
    console.log('modelId', this.modelId);

    this.list = [
      {id: 1, title: 'menu 1', parentId: 0},
      {id: 2, title: 'menu 2', parentId: 0},
      {id: 3, title: 'menu 3', parentId: 0},
      {id: 4, title: 'menu 1-1', parentId: 1},
    ];
  }

  chose(vi: NavigationVi) {
    this.choseTrigger.emit(vi);
  }
}
