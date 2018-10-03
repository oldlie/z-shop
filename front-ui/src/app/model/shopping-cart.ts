export interface ShoppingCartRequest {
    id: number;
    uid: number;
    commodityId: number;
    specId: number;
    count: number;
    price: number;
}

export interface ShoppingCartItem {
    shoppingCartId: number;
    uid?: number;
    username?: string;
    nickname?: string;
    commodityId: number;
    commodityTitle: string;
    commoditySummary?: string;
    specId: number;
    specTitle?: string;
    price: number;
    types: number;
    count: number;
}
