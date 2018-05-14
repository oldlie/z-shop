import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-commodity-spec',
  templateUrl: './spec.component.html',
  styleUrls: ['./spec.component.css']
})
export class SpecComponent implements OnInit {

  index = '2';
  @Input()
  commodityId: number;

  constructor() { }

  ngOnInit() {
  }

  handle(index: string) {
    this.index = index;
  }
}
