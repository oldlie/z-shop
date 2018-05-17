import { Component, OnInit, Input } from '@angular/core';
import { CommoditySpecVI } from '../commodity-vi';

@Component({
  selector: 'app-commodity-spec',
  templateUrl: './spec.component.html',
  styleUrls: ['./spec.component.css']
})
export class SpecComponent implements OnInit {

  index = '1';
  @Input()
  commodityId: number;
  editSpecification: CommoditySpecVI;

  constructor() { }

  ngOnInit() {
  }

  handle(index: string) {
    this.index = index;
    this.editSpecification = null;
  }

  editSpecListener(specification: CommoditySpecVI) {
    this.editSpecification = specification;
    console.log('editSpecification', specification);
    this.index = '2';
  }
}
