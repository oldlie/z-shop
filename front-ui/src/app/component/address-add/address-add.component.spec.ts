import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressAddComponent } from './address-add.component';

describe('AddressAddComponent', () => {
  let component: AddressAddComponent;
  let fixture: ComponentFixture<AddressAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddressAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddressAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
