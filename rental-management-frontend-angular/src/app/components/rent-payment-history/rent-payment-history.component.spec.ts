import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RentPaymentHistoryComponent } from './rent-payment-history.component';

describe('RentPaymentHistoryComponent', () => {
  let component: RentPaymentHistoryComponent;
  let fixture: ComponentFixture<RentPaymentHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RentPaymentHistoryComponent]
    });
    fixture = TestBed.createComponent(RentPaymentHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
