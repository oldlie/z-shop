import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayCardAddComponent } from './pay-card-add.component';

describe('PayCardAddComponent', () => {
  let component: PayCardAddComponent;
  let fixture: ComponentFixture<PayCardAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayCardAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayCardAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
