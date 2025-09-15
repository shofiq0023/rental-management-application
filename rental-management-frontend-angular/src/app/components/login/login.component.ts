import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginReqModel } from 'src/app/models/data-models/request-models/login-request.model';
import { LoginModel } from 'src/app/models/data-models/response-models/login-response.model';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { LoginReponseModel } from 'src/app/models/response/login-response.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent {
	private TOKEN_KEY: string = "token";
	private USER_TYPE: string = "userType";
	private USER_ID: string = "userId";
	private ROLE_AMDIN: string = "ROLE_ADMIN";
	constructor(
		private userAuthenticationService: AuthenticationService,
		private snackBar: MatSnackBar,
		private router: Router,
		private localStorageService: LocalStorageService) { }

	public message: string = '';
	public username: string = '';
	public password: string = '';
	public loading: boolean = false;

	public login(): void {
		this.loading = true;
		let loginReqObj: LoginReqModel = {
			username: this.username,
			password: this.password
		}

		this.userAuthenticationService.userAuth(loginReqObj).subscribe({
			next: (res) => {
				let apiResponse: LoginReponseModel = res;
				let data: LoginModel | undefined = apiResponse.data;

				if (data != undefined) {
					let userType: string = '';

					if (data.userInfo?.userType == undefined || data.userInfo?.userType == null) {
						userType = this.ROLE_AMDIN;
					} else {
						userType = data.userInfo.userType;
					}

					this.userAuthenticationService.changeUserType(userType);

					this.localStorageService.setItem(this.TOKEN_KEY, data.token);
					this.localStorageService.setItem(this.USER_TYPE, userType);
					this.localStorageService.setItem(this.USER_ID, data.userInfo?.id);
					this.loading = false;
					this.snackBar.open("Login successful", "", {
						duration: 3000,
						panelClass: ["snack-success"]
					});
					this.router.navigate(['/dashboard']);
				}
			},
			error: (err) => {
				this.loading = false;
				let responseData: ApiResponseModel = err.error;

				this.snackBar.open(responseData.message, "", {
					duration: 3000,
					panelClass: ["snack-fail"]
				});
			}
		});
	}
}
