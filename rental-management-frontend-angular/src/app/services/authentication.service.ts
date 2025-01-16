import { Injectable } from '@angular/core';
import { LoginReqModel } from '../models/data-models/login-request.model';
import { Observable, of } from 'rxjs';
import { LoginReponseModel } from '../models/response/login-response.model';
import { HttpClient } from '@angular/common/http';
import { api } from '../api-list';
import { LocalStorageService } from './local-storage.service';
import { LoginModel } from '../models/data-models/login-response.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  
  constructor(private http: HttpClient) { }

  // Login API call
  public userAuth(loginRequestObject: LoginReqModel): Observable<LoginReponseModel> {
    return this.http.post(api.USER_LOGIN_API, loginRequestObject);
  }

}
