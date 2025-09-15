import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NgbDate, NgbInputDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { UserSignupRequestModel } from 'src/app/models/data-models/request-models/user-signup.request.model';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';

@Component({
	selector: 'app-signup',
	templateUrl: './signup.component.html',
	styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
	public loading: boolean = false;
	public isPasswordMatching: boolean = true;

	public name: string = '';
	public username: string = '';
	public email: string = '';
	public phone: string = '';
	public password: string = '';
	public confirmPassword: string = '';
	public address: string = '';
	public dateOfBirth!: NgbDate;

	constructor(
		private config: NgbInputDatepickerConfig,
		private userAuthenticationService: AuthenticationService,
		private snackBar: MatSnackBar,
		private router: Router,
		private localStorageService: LocalStorageService) {
		this.config.autoClose = true;
		this.config.placement = ['top-start', 'top-end'];
	}

	public checkPassword(): void {
		if (this.password != this.confirmPassword) {
			this.isPasswordMatching = false;
		} else {
			this.isPasswordMatching = true;
		}
	}

	public signup(): void {
		this.loading = true;

		let userSignupReq: UserSignupRequestModel = {
			name: this.name,
			username: this.username,
			email: this.email,
			phone: this.phone,
			password: this.password,
			address: this.address,
			dateOfBirth: this.getDateString(this.dateOfBirth)
		}

		this.userAuthenticationService.userSignup(userSignupReq).subscribe({
			next: (res) => {
				this.loading = false;

				this.snackBar.open("Signup successful, Please login with the credentials", "", {
					duration: 3000,
					panelClass: ["snack-success"]
				});

				this.router.navigate(['/login']);
			},
			error: (err) => {
				this.loading = false;
				let responseData: ApiResponseModel = err.error;

				if (responseData.responseCode == '403') {
					this.snackBar.open(responseData.message, "", {
						duration: 5000,
						panelClass: ["snack-fail"]
					});
				} else {
					this.snackBar.open(responseData.message, "", {
						duration: 3000,
						panelClass: ["snack-fail"]
					});
				}

				
			}
		});
	}

	private getDateString(date: NgbDate): string {
		// Get date string as '28/02/1999'
		return `${this.getFullNumber(date.day)}-${this.getFullNumber(date.month)}-${this.getFullNumber(date.year)}`;
	}

	private getFullNumber(date: number): string {
		if (date < 10) {
			return `0${date}`;
		} else {
			return date.toString();
		}
	}
}
