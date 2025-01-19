import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbInputDatepickerConfig, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { UserSignupRequestModel } from 'src/app/models/data-models/request-models/user-signup.request.model';
import { UserModel } from 'src/app/models/data-models/user.model';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ToastService } from 'src/app/services/toast.service';
import { UsersAddComponent } from '../users-add/users-add.component';

@Component({
	selector: 'app-renters-add',
	templateUrl: './renters-add.component.html',
	styleUrls: ['./renters-add.component.scss']
})
export class RentersAddComponent {
	constructor(
		private config: NgbInputDatepickerConfig,
		private userAuthenticationService: AuthenticationService,
		private toastService: ToastService,
		private router: Router) {
		this.config.autoClose = true;
		this.config.placement = ['top-start', 'top-end'];
	}

	public ngOnInit(): void {
		this.getUsers();
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
	public dateOfBirth!: NgbDate;

	public usersList: UserModel[] = [];

	public getUsers(): void {
		this.userAuthenticationService.getAllUser().subscribe({
			next: (res) => {
				this.isLoading = false;
				let apiResponse: ApiResponseModel = res;
				let data: any[] = apiResponse.data;

				for (const d in data) {
					this.usersList.push(data[d]);
				}

				this.usersList = this.usersList.filter((u) => u.userType != 'ADMIN');

			},
			error: (err) => {
				this.isLoading = false;
				let responseData: ApiResponseModel = err.error;

				this.toastService.showFailToast(responseData.message, 5000);
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
			dateOfBirth: this.getDateString(this.dateOfBirth),
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
