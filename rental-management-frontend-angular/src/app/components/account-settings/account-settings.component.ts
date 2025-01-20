import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faMinus, faPlus } from '@fortawesome/free-solid-svg-icons';
import { NgbInputDatepickerConfig, NgbDate, NgbDatepicker } from '@ng-bootstrap/ng-bootstrap';
import { FlatsModel } from 'src/app/models/data-models/flats.model';
import { UserSignupRequestModel } from 'src/app/models/data-models/request-models/user-signup.request.model';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { BuildingService } from 'src/app/services/building.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
	selector: 'app-account-settings',
	templateUrl: './account-settings.component.html',
	styleUrls: ['./account-settings.component.scss']
})
export class AccountSettingsComponent {
	constructor(
		private config: NgbInputDatepickerConfig,
		private userAuthenticationService: AuthenticationService,
		private toastService: ToastService,
		private router: Router) {
		this.config.autoClose = true;
		this.config.placement = ['top-start', 'top-end'];
	}

	public ngOnInit(): void {
		this.getSingleUser();
	}

	public isLoading: boolean = false;

	public isPasswordMatching: boolean = true;

	public name: string = '';
	public username: string = '';
	public email: string = '';
	public phone: string = '';
	public password: string = '';
	public confirmPassword: string = '';
	public address: string = '';
	public userType: number = 1;

	public getSingleUser(): void {
		this.isLoading = true;

		this.userAuthenticationService.getSingleUserInfo().subscribe({
			next: (res) => {
				this.isLoading = false;
				let apiResponse: ApiResponseModel = res;
				let userRes: any = apiResponse.data;

				if (userRes != undefined) {
					this.name = userRes.name;
					this.username = userRes.username;
					this.email = userRes.email;
					this.phone = userRes.phone;
					this.address = userRes.address;
				}

				// this.toastService.showSuccessToast("User created sucecssfully");
				// this.router.navigate(['/users-list']);
			},
			error: (err) => {
				this.isLoading = false;
				let responseData: ApiResponseModel = err.error;

				if (responseData.responseCode == '403') {
					this.toastService.showFailToast(responseData.message, 5000);
				} else {
					this.toastService.showFailToast(responseData.message);
				}
			}
		});
	}

	public checkPassword(): void {
		if (this.password != this.confirmPassword) {
			this.isPasswordMatching = false;
		} else {
			this.isPasswordMatching = true;
		}
	}

	public createAccount(): void {
		this.isLoading = true;

		let userSignupReq: UserSignupRequestModel = {
			name: this.name,
			username: this.username,
			email: this.email,
			phone: this.phone,
			password: this.password,
			address: this.address,
			// dateOfBirth: this.getDateString(this.dateOfBirth),
			userType: this.userType
		}

		this.userAuthenticationService.userCreate(userSignupReq).subscribe({
			next: (res) => {
				this.isLoading = false;

				this.toastService.showSuccessToast("User created sucecssfully");
				this.router.navigate(['/users-list']);
			},
			error: (err) => {
				this.isLoading = false;
				let responseData: ApiResponseModel = err.error;

				if (responseData.responseCode == '403') {
					this.toastService.showFailToast(responseData.message, 5000);
				} else {
					this.toastService.showFailToast(responseData.message);
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
