import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayCardSoldComponent } from './pay-card-sold.component';

describe('PayCardSoldComponent', () => {
  let component: PayCardSoldComponent;
  let fixture: ComponentFixture<PayCardSoldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayCardSoldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayCardSoldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
