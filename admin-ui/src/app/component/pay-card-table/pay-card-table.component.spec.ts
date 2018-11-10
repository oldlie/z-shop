import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayCardTableComponent } from './pay-card-table.component';

describe('PayCardTableComponent', () => {
  let component: PayCardTableComponent;
  let fixture: ComponentFixture<PayCardTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayCardTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayCardTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
