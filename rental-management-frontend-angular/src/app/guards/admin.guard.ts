import { CanActivateFn, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { inject } from '@angular/core';
import { LocalStorageService } from '../services/local-storage.service';

export const adminGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
	const router: Router = inject(Router);
	const localStorageService = new LocalStorageService();

	return localStorageService.isUserAdmin() ? true : router.navigate(['/dashboard']);
};
