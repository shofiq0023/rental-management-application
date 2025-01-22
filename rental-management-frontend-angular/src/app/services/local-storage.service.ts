import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class LocalStorageService {
	private TOKEN: string = 'token';
	private USER_TYPE: string = 'userType';

	constructor() { }

	public isUserLoggedIn(): boolean {
		if (this.getItem(this.TOKEN) == undefined || this.getItem(this.TOKEN) == '') {
			return false;
		}

		return true;
	}

	public isUserRenter(): boolean {
		if (this.getItem(this.USER_TYPE) == undefined || this.getItem(this.USER_TYPE) == 'ROLE_ADMIN') {
			return false;
		}

		return true;
	}

	public isUserAdmin(): boolean {
		if (this.getItem(this.USER_TYPE) == undefined || this.getItem(this.USER_TYPE) == 'ROLE_ADMIN') {
			return true;
		}

		return false;
	}

	public setItem(key: string, value: string | undefined): void {
		localStorage.setItem(key, value == undefined ? '' : value);
	}

	public getItem(key: string): string {
		let localData: string | null = localStorage.getItem(key);

		if (localData == null) {
			return "";
		}

		return localData;
	}

	public removeItem(key: string): void {
		localStorage.removeItem(key);
	}

	public clear(): void {
		localStorage.clear();
	}
}
