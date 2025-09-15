import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { rentersGuard } from './renters.guard';

describe('rentersGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => rentersGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
