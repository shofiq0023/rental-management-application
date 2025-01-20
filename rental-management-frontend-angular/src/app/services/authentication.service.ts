import { Injectable } from '@angular/core';
import { LoginReqModel } from '../models/data-models/request-models/login-request.model';
import { Observable, of } from 'rxjs';
import { LoginReponseModel } from '../models/response/login-response.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { api } from '../api-list';
import { UserSignupRequestModel } from '../models/data-models/request-models/user-signup.request.model';
import { UserModel } from '../models/data-models/user.model';
import { LocalStorageService } from './local-storage.service';

@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {
	private TOKEN: string = 'token';
	private USER_TYPE: string = 'userType';

	constructor(private http: HttpClient, private localStorageService: LocalStorageService) {
		this.userType = this.localStorageService.getItem(this.USER_TYPE);
	}

	public userType: string;

	private getJwtHeader(): HttpHeaders {
		const httpHeaders: HttpHeaders = new HttpHeaders({
			Authorization: `Bearer ${this.localStorageService.getItem(this.TOKEN)}`
		});

		return httpHeaders;
	}

	public getUserType(): Observable<string> {
		let userType = this.localStorageService.getItem(this.USER_TYPE);

		if (userType == undefined || userType == '') {
			return of(this.userType);
		}

		return of(this.userType);
	}

	public changeUserType(userType: string): void {
		this.userType = userType;
	}

	// Login API call
	public userAuth(loginRequestObject: LoginReqModel): Observable<LoginReponseModel> {
		return this.http.post(api.USER_LOGIN_API, loginRequestObject);
	}

	// User signup API call
	public userSignup(userSignupRequest: UserSignupRequestModel): Observable<UserModel> {
		return this.http.post(api.USER_SIGNUP_API, userSignupRequest);
	}

	public userCreate(userSignupRequest: UserSignupRequestModel): Observable<UserModel> {
		return this.http.post(api.USER_CREATE_API, userSignupRequest, { headers: this.getJwtHeader() });
	}

	public getAllUser(): Observable<any> {
		return this.http.get(api.USER_LIST_ALL, { headers: this.getJwtHeader() });
	}

	public deleteUser(userId: number): Observable<any> {
		return this.http.delete(api.USER_DELETE + userId, { headers: this.getJwtHeader() });
	}

	public getAllInactiveUsers(): Observable<any> {
		return this.http.get(api.USER_INACTIVE, { headers: this.getJwtHeader() });
	}

	public activateUser(userId: number): Observable<any> {
		return this.http.post(api.USER_ACTIVATE + userId, userId, { headers: this.getJwtHeader() });
	}

	public getSingleUserInfo(): Observable<any> {
		return this.http.get(api.USER_GET_SINGLE, { headers: this.getJwtHeader() });
	}

	public updateUserInfo(userUpdateReqModel: any, userId: number) {
		return this.http.put(api.USER_UPDATE + userId, userUpdateReqModel, { headers: this.getJwtHeader() });
	}

	public updateUserPassword(passwordUpdateReqModel: any, userId: number) {
		return this.http.put(api.USER_PASSWORD_UPDATE + userId, passwordUpdateReqModel, { headers: this.getJwtHeader() });
	}

}
