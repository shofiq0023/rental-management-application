import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';
import { Observable } from 'rxjs';
import { api } from '../api-list';

@Injectable({
	providedIn: 'root'
})
export class RentersService {
	
	
	private TOKEN: string = 'token';

	constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

	private getJwtHeader(): HttpHeaders {
		const httpHeaders: HttpHeaders = new HttpHeaders({
			Authorization: `Bearer ${this.localStorageService.getItem(this.TOKEN)}`
		});

		return httpHeaders;
	}

	public createRenter(reqObj: any): Observable<any> {
		return this.http.post(api.RENTERS_CREATE, reqObj, { headers: this.getJwtHeader() });
	}

	public getAllRenters(): Observable<any> {
		return this.http.get(api.RENTERS_LIST_SIMPLE, { headers: this.getJwtHeader() });
	}
	public deleteRenter(renterId: number, buildingFlatId: number): Observable<any> {
		return this.http.delete(api.RENTERS_DELETE + `?renterId=${renterId}&buildingFlatId=${buildingFlatId}`, { headers: this.getJwtHeader() });
	}
}
