import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RenterRentPaymentAddComponent } from './renter-rent-payment-add.component';

describe('RenterRentPaymentAddComponent', () => {
  let component: RenterRentPaymentAddComponent;
  let fixture: ComponentFixture<RenterRentPaymentAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RenterRentPaymentAddComponent]
    });
    fixture = TestBed.createComponent(RenterRentPaymentAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
