import { Injectable } from '@angular/core';
import { LoginReqModel } from '../models/data-models/request-models/login-request.model';
import { Observable, of } from 'rxjs';
import { LoginReponseModel } from '../models/response/login-response.model';
import { HttpClient } from '@angular/common/http';
import { api } from '../api-list';
import { UserSignupRequestModel } from '../models/data-models/request-models/user-signup.request.model';
import { UserModel } from '../models/data-models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  
  constructor(private http: HttpClient) { }

  // Login API call
  public userAuth(loginRequestObject: LoginReqModel): Observable<LoginReponseModel> {
    return this.http.post(api.USER_LOGIN_API, loginRequestObject);
  }

  // User signup API call
  public userSignup(userSignupRequest: UserSignupRequestModel): Observable<UserModel> {
    return this.http.post(api.USER_SIGNUP_API, userSignupRequest);
  }

}
