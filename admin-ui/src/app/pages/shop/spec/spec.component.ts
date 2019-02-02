import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CommoditySpecVI } from '../commodity-vi';

@Component({
  selector: 'app-commodity-spec',
  templateUrl: './spec.component.html',
  styleUrls: ['./spec.component.css']
})
export class SpecComponent implements OnInit {

  index = 0;
  @Input()
  commodityId = 0;
  editSpecification: CommoditySpecVI;
  @Output()
  checkSpecEvent = new EventEmitter<CommoditySpecVI>();

  constructor() { }

  ngOnInit() {
  }

  handle(index: number) {
    if (index === 0) {
      this.editSpecification = null;
    }
  }

  editSpecListener(specification: CommoditySpecVI) {
    this.editSpecification = specification;
    this.index = 1;
  }

  checkSpecListener(specification: CommoditySpecVI) {
    this.checkSpecEvent.emit(specification);
  }
}
