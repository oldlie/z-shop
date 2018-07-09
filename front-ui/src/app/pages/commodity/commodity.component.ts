import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Commodity } from '../../model/response';

@Component({
  selector: 'app-commodity',
  templateUrl: './commodity.component.html',
  styleUrls: ['./commodity.component.css']
})
export class CommodityComponent implements OnInit {

  commodityId = 0;
  commodity: Commodity;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.commodityId = Number(this.route.snapshot.paramMap.get('id'));
  }

  private loadCommodity() {
    
  }
}
