import { Component, OnInit } from '@angular/core';
import { Commodity } from '../../../response/commodity.response';
import { CommodityService } from '../../../services/commodity.service';

@Component({
  selector: 'app-home-commodity',
  templateUrl: './commodity.component.html',
  styleUrls: ['./commodity.component.css'],
  providers: [
    CommodityService
  ]
})
export class CommodityComponent implements OnInit {

  list = new Array<Commodity>();

  constructor(private commodity: CommodityService) { }

  ngOnInit() {
  }

  loadCommodity() {
    
  }
}
