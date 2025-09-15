import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RentersAddComponent } from './renters-add.component';

describe('RentersAddComponent', () => {
  let component: RentersAddComponent;
  let fixture: ComponentFixture<RentersAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RentersAddComponent]
    });
    fixture = TestBed.createComponent(RentersAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
