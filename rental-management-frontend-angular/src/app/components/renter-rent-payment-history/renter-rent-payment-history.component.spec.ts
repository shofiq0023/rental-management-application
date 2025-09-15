import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RenterRentPaymentHistoryComponent } from './renter-rent-payment-history.component';

describe('RenterRentPaymentHistoryComponent', () => {
  let component: RenterRentPaymentHistoryComponent;
  let fixture: ComponentFixture<RenterRentPaymentHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RenterRentPaymentHistoryComponent]
    });
    fixture = TestBed.createComponent(RenterRentPaymentHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
