import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { ToastService } from 'src/app/services/toast.service';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { RentersService } from 'src/app/services/renters.service';
import { RentersResponseModel } from 'src/app/models/data-models/response-models/renters.response.model';

@Component({
	selector: 'app-renters-list',
	templateUrl: './renters-list.component.html',
	styleUrls: ['./renters-list.component.scss']
})
export class RentersListComponent {
	constructor(private toastService: ToastService, public dialog: MatDialog, private rentersService: RentersService) { }

	public ngOnInit(): void {
		this.getRenters();
	}

	public displayedColumns: string[] = ['num', 'renterName', 'buildingName', 'flatNo', 'renterPhone', 'renterEmail', 'renterAddress', 'nidNo', 'actions'];
	public listData!: MatTableDataSource<RentersResponseModel>;

	@ViewChild(MatSort)
	private sort!: MatSort;

	@ViewChild(MatPaginator)
	private paginator!: MatPaginator;

	public searchKey!: string;
	public isLoading: boolean = true;

	public openEditDialog(data: any): void {

	}

	public openDialog(renterId: number, buildingFlatId: number): void {
		// Opens the dialog box for confirmation
		const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
			data: {
				message: 'Are you sure want to delete this user?',
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

					this.rentersService.deleteRenter(renterId, buildingFlatId).subscribe({
						next: (res) => {
							this.isLoading = false;
							let apiResponse: ApiResponseModel = res;

							this.toastService.showSuccessToast(apiResponse.message);
							this.getRenters();
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

	public getRenters(): void {
		this.rentersService.getAllRenters().subscribe({
			next: (res) => {
				this.isLoading = false;
				let apiResponse: ApiResponseModel = res;
				let data: any = apiResponse.data;

				this.listData = new MatTableDataSource(data);
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
}
