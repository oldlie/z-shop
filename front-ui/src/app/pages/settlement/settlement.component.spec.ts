import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SettlementComponent } from './settlement.component';

describe('SettlementComponent', () => {
  let component: SettlementComponent;
  let fixture: ComponentFixture<SettlementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SettlementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SettlementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
