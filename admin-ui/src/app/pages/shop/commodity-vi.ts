/*
Commodity View Interface
*/

export interface CommodityVI {
  id: number;
  title: string;
  summary: string;
  comment?: string;
  description: string;
  status: number;
  ranking: number;
  rankingCount: number;
  viewCount: number;
  shareCount: number;
  createAt?: Date;
  updateAt?: Date;
}

export enum CommodityStatus {
  init = 0,
  online = 1,
  offline= 2
}

export interface CommoditySpecVI {
  id?: number;
  title: string;
  commodityId?: number;
  breed?: string;
  origin?: string;
  feature?: string;
  spec: string;
  store?: string;
  productDatetime?: string;
  price: number;
  inventory: number;
}
