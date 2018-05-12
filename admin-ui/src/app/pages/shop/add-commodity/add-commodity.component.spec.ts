import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCommodityComponent } from './add-commodity.component';

describe('AddCommodityComponent', () => {
  let component: AddCommodityComponent;
  let fixture: ComponentFixture<AddCommodityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCommodityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCommodityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
