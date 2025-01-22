import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { RentPaymentResponseModel } from 'src/app/models/data-models/response-models/rent-payment.response.model';
import { UserModel } from 'src/app/models/data-models/user.model';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { RentPaymentService } from 'src/app/services/rent-payment.service';
import { ToastService } from 'src/app/services/toast.service';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';

@Component({
	selector: 'app-renter-rent-payment-history',
	templateUrl: './renter-rent-payment-history.component.html',
	styleUrls: ['./renter-rent-payment-history.component.scss']
})
export class RenterRentPaymentHistoryComponent {
constructor(private toastService: ToastService,
		public dialog: MatDialog,
		private userAuthenticationService: AuthenticationService,
		private rentPaymentService: RentPaymentService) { }

	public ngOnInit(): void {
		this.getRentPaymentHistory();
		// this.getAllUsers();
	}
	@ViewChild(MatSort)
	private sort!: MatSort;

	@ViewChild(MatPaginator)
	private paginator!: MatPaginator;

	public displayedColumns: string[] = ['num', 'renter', 'phone', 'building', 'flat', 'amount', 'utilityBill', 'othersBill', 'monthName', 'yearStr', 'type', 'actions'];
	public listData!: MatTableDataSource<RentPaymentResponseModel>;
	public apiData: any[] = [];
	public searchKey!: string;
	public isLoading: boolean = true;
	public userList: UserModel[] = [];
	public userId: number | string = '';




	public getRentPaymentHistory(): void {
		this.rentPaymentService.getAllRentPaymentsByUserId().subscribe({
			next: (res) => {
				this.isLoading = false;
				let apiResponse: ApiResponseModel = res;
				let data: any[] = apiResponse.data;

				console.log(data);

				this.apiData = data;
				this.listData = new MatTableDataSource(this.apiData);
				this.listData.sort = this.sort;
				this.listData.paginator = this.paginator;
			},
			error: (err) => {
				this.isLoading = false;
				let responseData: ApiResponseModel = err.error;

				this.toastService.showFailToast(responseData.message, 5000);
			}
		});
	}

	public formatToCurrency(amount: number): string {
		return new Intl.NumberFormat('en-IN').format(amount);
	}

	public openDialog(paymentId: number): void {
		// Opens the dialog box for confirmation
		const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
			data: {
				message: 'Are you sure want to delete this payment information?',
				buttonText: {
					ok: 'Yes',
					cancel: 'No'
				}
			}
		});

		dialogRef.afterClosed().subscribe({
			next: (confirmed: boolean) => {
				if (confirmed) {
					this.isLoading = true;

					this.rentPaymentService.deleteRentPayment(paymentId).subscribe({
						next: (res) => {
							this.isLoading = false;
							let apiResponse: ApiResponseModel = res;

							this.toastService.showSuccessToast(apiResponse.message);
							this.getRentPaymentHistory();
						},
						error: (err) => {
							this.isLoading = false;
							let responseData: ApiResponseModel = err.error;

							this.toastService.showFailToast(responseData.message);
						}
					});
				}
			},
		});
	}
}
