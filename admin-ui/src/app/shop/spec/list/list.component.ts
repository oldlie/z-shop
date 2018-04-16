import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-commodity-spec-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  tableData: any[];

  constructor() { }

  ngOnInit() {
    this.tableData = [
      {id: 1, title: 'title 1', spec: 'spec 1', price: 1.00, inventory: 10},
      {id: 2, title: 'title 2', spec: 'spec 2', price: 11.00, inventory: 20},
      {id: 3, title: 'title 3', spec: 'spec 3', price: 11.00, inventory: 30},
      {id: 4, title: 'title 4', spec: 'spec 4', price: 111.00, inventory: 40},
    ];
  }

  chose(data: any) {
    console.log('chose', this.tableData[data.index]);
  }

  update(data: any) {
    console.log('update', data);
  }

  delete(data: any) {
    console.log('delete', data);
  }
}
