import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersApproveComponent } from './users-approve.component';

describe('UsersApproveComponent', () => {
  let component: UsersApproveComponent;
  let fixture: ComponentFixture<UsersApproveComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UsersApproveComponent]
    });
    fixture = TestBed.createComponent(UsersApproveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
