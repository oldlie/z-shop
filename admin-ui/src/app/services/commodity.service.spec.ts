import { TestBed, inject } from '@angular/core/testing';

import { CommodityService } from './commodity.service';

describe('CommodityService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CommodityService]
    });
  });

  it('should be created', inject([CommodityService], (service: CommodityService) => {
    expect(service).toBeTruthy();
  }));
});
