import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbInputDatepickerConfig, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { filter } from 'rxjs';
import { FlatsModel } from 'src/app/models/data-models/flats.model';
import { UserSignupRequestModel } from 'src/app/models/data-models/request-models/user-signup.request.model';
import { BuildingsResponseModel } from 'src/app/models/data-models/response-models/buildings.response.model';
import { FlatsResponseModel } from 'src/app/models/data-models/response-models/flats.response.model';
import { UserModel } from 'src/app/models/data-models/user.model';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { BuildingService } from 'src/app/services/building.service';
import { RentersService } from 'src/app/services/renters.service';
import { ToastService } from 'src/app/services/toast.service';

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
		private router: Router,
		private buildingService: BuildingService,
		private rentersService: RentersService) {
		this.config.autoClose = true;
		this.config.placement = ['top-start', 'top-end'];
	}

	public ngOnInit(): void {
		this.getUsers();
		this.getBuildings();
	}

	public isLoading: boolean = false;

	public isPasswordMatching: boolean = true;

	public userId?: number;
	public buildingFlatId?: number;
	public renterNid: string = '';
	public renterDeal: string = '';
	public selectedBuildingId?: number;

	public selectedUser?: UserModel;
	public usersList: UserModel[] = [];
	public buildingsList: BuildingsResponseModel[] = [];
	public selectedFlats: FlatsResponseModel[] = [];

	public getBuildings(): void {
		this.buildingService.getBuildings().subscribe({
			next: (res) => {
				this.isLoading = false;
				let apiResponse: ApiResponseModel = res;
				let buildingsRes: any = apiResponse.data;

				for (const building in buildingsRes) {
					this.buildingsList.push(buildingsRes[building]);
				}

			},
			error: (err) => {
				this.isLoading = false;
				let responseData: ApiResponseModel = err.error;

				this.toastService.showFailToast(responseData.message);
			}
		});
	}

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

	public filterFalts(): void {
		let filterdBuilding: BuildingsResponseModel = this.buildingsList.filter((b) => b.id == this.selectedBuildingId)[0];
		let flats: FlatsResponseModel[] | undefined = filterdBuilding.flats;

		if (flats != undefined) {
			flats = flats.filter(f => !f.rented);
			this.selectedFlats = flats;
		}
	}

	public changeSelectedUser() {
		this.selectedUser = this.usersList.filter((u) => u.id == this.userId)[0];
	}

	public createRenter() {
		this.isLoading = true;

		let userSignupReq = {
			userId: this.userId,
  			buildingFlatId: this.buildingFlatId,
  			nidNo: this.renterNid,
  			deal: this.renterDeal
		}

		this.rentersService.createRenter(userSignupReq).subscribe({
			next: (res) => {
				this.isLoading = false;

				this.toastService.showSuccessToast("Renter created sucecssfully");
				this.router.navigate(['/renter-list']);
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
