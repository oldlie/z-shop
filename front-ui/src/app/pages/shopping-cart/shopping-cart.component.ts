import { Component, OnInit } from '@angular/core';
import { FrontService } from '../../service/front.service';
import { ShoppingCartItem } from '../../model/shopping-cart';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css'],
  providers: [
    FrontService
  ]
})
export class ShoppingCartComponent implements OnInit {

  list: Array<ShoppingCartItem>;

  constructor(private fs: FrontService, private r: Router) { }

  ngOnInit() {
    this.fs.findMyShoppingCartItemList().then(x => {
      if (x.status === 0) {
        this.list = x.list;
      } else {
        console.error('ShoppingCartComponent->findMyShoppingCartItemList:', x);
      }
    });
  }

  delete(item: ShoppingCartItem) {
    this.fs.deleteShoppingCartItem(item.shoppingCartId).then(x => {
      if (x.status === 0) {
        const temp = this.list.filter(_item => _item.shoppingCartId !== item.shoppingCartId);
        this.list = temp;
      }
    }).catch(err => { console.error(err); });
  }

  settlement() {
    this.r.navigate(['/settlement']).catch(err => console.error(err));
  }
}
