import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { api } from '../api-list';
import { LocalStorageService } from './local-storage.service';

@Injectable({
	providedIn: 'root'
})
export class RentPaymentService {
	private TOKEN: string = 'token';

	constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

	private getJwtHeader(): HttpHeaders {
		const httpHeaders: HttpHeaders = new HttpHeaders({
			Authorization: `Bearer ${this.localStorageService.getItem(this.TOKEN)}`
		});

		return httpHeaders;
	}

	public createRentPayment(reqObj: any): Observable<any> {
		return this.http.post(api.RENT_PAYMENT_CREATE, reqObj, { headers: this.getJwtHeader() });
	}

	public getAllRentPayments(): Observable<any> {
		return this.http.get(api.RENT_PAYMENT_GET_ALL, { headers: this.getJwtHeader() });
	}

	public deleteRentPayment(paymentId: number): Observable<any> {
		return this.http.delete(api.RENT_PAYMENT_DELETE + paymentId, { headers: this.getJwtHeader() });
	}
}
