import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbInputDatepickerConfig, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { RentersResponseModel } from 'src/app/models/data-models/response-models/renters.response.model';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { RentPaymentService } from 'src/app/services/rent-payment.service';
import { RentersService } from 'src/app/services/renters.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
	selector: 'app-renter-rent-payment-add',
	templateUrl: './renter-rent-payment-add.component.html',
	styleUrls: ['./renter-rent-payment-add.component.scss']
})
export class RenterRentPaymentAddComponent {
constructor(
		private config: NgbInputDatepickerConfig,
		private toastService: ToastService,
		private router: Router,
		private rentersService: RentersService,
		private rentPaymentService: RentPaymentService) {
		this.config.autoClose = true;
		this.config.placement = ['top-start', 'top-end'];
	}

	public ngOnInit(): void {
		this.getRenters();
	}

	public isLoading: boolean = false;
	public isPasswordMatching: boolean = true;
	public renterId?: number;

	public rentAmount?: number;
	public utilityBill?: number;
	public othersBill?: number;
	public paymentStatus: number = 1;
	public paymentDate!: NgbDate;

	public selectedRenter?: RentersResponseModel;
	public rentersList: RentersResponseModel[] = [];


	public getRenters(): void {
		this.rentersService.getAllRentersByUserId().subscribe({
			next: (res) => {
				this.isLoading = false;
				let apiResponse: ApiResponseModel = res;
				let data: any[] = apiResponse.data;

				this.rentersList = [];

				for (let i = 0; i < data.length; i++) {
					this.rentersList.push(data[i]);
				}
			},
			error: (err) => {
				this.isLoading = false;
				let responseData: ApiResponseModel = err.error;

				this.toastService.showFailToast(responseData.message, 5000);
			}
		});
	}

	public changeSelectedRenter() {
		this.selectedRenter = this.rentersList.filter((r) => r.renterId == this.renterId)[0];
	}

	public addRentPayment(): void {
		this.isLoading = true;

		let rentPaymentReq = {
			amount: this.rentAmount,
			utilityBill: this.utilityBill,
			othersBill: this.othersBill,
			renterId: this.selectedRenter?.renterId,
			userId: this.selectedRenter?.userId,
			paymentStatus: this.paymentStatus,
			rentPaymentDate: this.getDateString(this.paymentDate),
		}

		this.rentPaymentService.createRentPayment(rentPaymentReq).subscribe({
			next: () => {
				this.isLoading = false;

				this.toastService.showSuccessToast("Payment created successfully");
				this.router.navigate(['/renter-rent-payment-history']);
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
		if (date != undefined) {
			return `${this.getFullNumber(date.day)}-${this.getFullNumber(date.month)}-${this.getFullNumber(date.year)}`;
		} else {
			return '';
		}
	}

	private getFullNumber(date: number): string {
		if (date < 10) {
			return `0${date}`;
		} else {
			return date.toString();
		}
	}
}
