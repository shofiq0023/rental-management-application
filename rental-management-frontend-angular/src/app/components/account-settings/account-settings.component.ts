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
	constructor(private userAuthenticationService: AuthenticationService, private toastService: ToastService) {
		this.userId = 0;
	}

	public ngOnInit(): void {
		this.getSingleUser();
	}

	public isLoading: boolean = false;
	public isPasswordMatching: boolean = true;

	private userId: number;
	public name: string = '';
	public username: string = '';
	public email: string = '';
	public phone: string = '';

	public oldPassword: string = '';
	public newPassword: string = '';
	public confirmNewPassword: string = '';

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
					this.userId = userRes.id;
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
		if (this.newPassword != this.confirmNewPassword) {
			this.isPasswordMatching = false;
		} else {
			this.isPasswordMatching = true;
		}
	}

	public updateAccountInfo(): void {
		this.isLoading = true;

		let userUpdateReqModel = {
			name: this.name,
			address: this.address,
		}

		this.userAuthenticationService.updateUserInfo(userUpdateReqModel, this.userId).subscribe({
			next: () => {
				this.isLoading = false;

				this.toastService.showSuccessToast("User updated successfully");
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

	public updatePassword(): void {
		this.isLoading = true;

		let passwordUpdateReqModel = {
			oldPassword: this.oldPassword,
			newPassword: this.newPassword,
		}

		this.userAuthenticationService.updateUserPassword(passwordUpdateReqModel, this.userId).subscribe({
			next: () => {
				this.isLoading = false;

				this.toastService.showSuccessToast("Password updated successfully");
				this.resetPasswordForm();
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

	private resetPasswordForm(): void {
		this.oldPassword = '';
		this.newPassword = '';
		this.confirmNewPassword = '';
	}
}
