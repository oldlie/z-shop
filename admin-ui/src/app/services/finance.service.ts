import { Injectable } from '@angular/core';
import { CoreService } from './core.service';
import { PageResponse } from '../response/response';
import { PayCard } from '../response/pay-card';

@Injectable()
export class FinanceService {

    constructor(private cs: CoreService) {}

    test() {
        const url = `${this.cs.UrlPrefix}/admin/article/list`;
        return this.cs.get(url, { page: 1, size: 1, orderBy: 'id', order: 1 })
            .toPromise().then(x => { console.log('Test', x); return x; });
    }

    listPayCards(page: number, size: number, orderBy: string, order: String): Promise<any> {
        const url = `${this.cs.UrlPrefix}/admin/finance/pay-card`;
        return this.cs.get(url, {
          page: page,
          size: size,
          orderBy: orderBy,
          order: order
        }).toPromise().then(x => { console.log('PayCard:', x); return x; });
    }
}
