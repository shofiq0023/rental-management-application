import { CanActivateFn, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { inject } from '@angular/core';
import { LocalStorageService } from '../services/local-storage.service';

export const rentersGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
	const router: Router = inject(Router);
	const localStorageService = new LocalStorageService();

	return localStorageService.isUserRenter() ? true : router.navigate(['/dashboard']);
};
