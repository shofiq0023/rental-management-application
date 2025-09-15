import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';
import { Observable } from 'rxjs';
import { BuildingsResponseModel } from '../models/data-models/response-models/buildings.response.model';
import { api } from '../api-list';
import { ApiResponseModel } from '../models/response/api-response.model';

@Injectable({
	providedIn: 'root'
})
export class BuildingService {

	private TOKEN: string = 'token';

	constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

	private getJwtHeader(): HttpHeaders {
		const httpHeaders: HttpHeaders = new HttpHeaders({
			Authorization: `Bearer ${this.localStorageService.getItem(this.TOKEN)}`
		});

		return httpHeaders;
	}

	public getBuildings(): Observable<any> {
		return this.http.get(api.BUILDINGS_GET_ALL_API, { headers: this.getJwtHeader() });
	}

	public addBuilding(body: any): Observable<any> {
		return this.http.post(api.BUILDINGS_CREATE_API, body, { headers: this.getJwtHeader() });
	}

	public deleteBuilding(id: number): Observable<any> {
		return this.http.delete(api.BUILDINGS_DELETE + id, { headers: this.getJwtHeader() });
	}
}
