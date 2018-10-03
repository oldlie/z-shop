import { Injectable } from '@angular/core';
import { CoreService } from './core.service';
import { BaseResponse } from '../model/response';
import { ShoppingCartRequest, ShoppingCartItem } from '../model/shopping-cart';
import { SimpleResponse } from '../model/simple.response';
import { ListResponse } from '../model/list.response';

@Injectable()
export class FrontService {

    constructor(private cs: CoreService) { }

    addCommodity2ShoppingCart(request: ShoppingCartRequest): Promise<SimpleResponse<number>> {
        const url = `${this.cs.Config.apiURI}/front/shopping/cart`;
        return this.cs.post(url, request).toPromise().then(x => x as SimpleResponse<number>);
    }

    deleteShoppingCartItem(id: number): Promise<BaseResponse> {
        const url = `${this.cs.Config.apiURI}/front/shopping/cart`;
        return this.cs.delete(url, {id: id}).toPromise().then(x => x as BaseResponse);
    }

    findMyShoppingCartItemList(): Promise<ListResponse<ShoppingCartItem>> {
        const url = `${this.cs.Config.apiURI}/front/shopping/cart`;
        console.log('findMyShoppingCartItemList', this.cs.ProfileInfo.id);
        return this.cs.get(url, {uid: this.cs.ProfileInfo.id}).toPromise().then(x => x as ListResponse<ShoppingCartItem>);
    }
}
