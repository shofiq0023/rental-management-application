import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RentPaymentAddComponent } from './rent-payment-add.component';

describe('RentPaymentAddComponent', () => {
  let component: RentPaymentAddComponent;
  let fixture: ComponentFixture<RentPaymentAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RentPaymentAddComponent]
    });
    fixture = TestBed.createComponent(RentPaymentAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
