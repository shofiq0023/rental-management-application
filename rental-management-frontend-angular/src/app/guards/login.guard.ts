import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { LocalStorageService } from '../services/local-storage.service';

export const loginGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  const router: Router = inject(Router);
  const localStorageService = new LocalStorageService();

  return localStorageService.isUserLoggedIn() ? router.navigate(['/dashboard']) : true;
};
