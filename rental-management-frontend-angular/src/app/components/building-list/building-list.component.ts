import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { BuildingsResponseModel } from 'src/app/models/data-models/response-models/buildings.response.model';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { BuildingService } from 'src/app/services/building.service';
import { ToastService } from 'src/app/services/toast.service';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';

@Component({
	selector: 'app-building-list',
	templateUrl: './building-list.component.html',
	styleUrls: ['./building-list.component.scss']
})
export class BuildingListComponent {
	constructor(private buildingService: BuildingService, private toastService: ToastService, public dialog: MatDialog) { }

	public ngOnInit(): void {
		this.getBuildings();
	}

	public displayedColumns: string[] = ['num', 'name', 'address', 'flats', 'actions'];
	public listData!: MatTableDataSource<BuildingsResponseModel>;

	@ViewChild(MatSort)
	private sort!: MatSort;

	@ViewChild(MatPaginator)
	private paginator!: MatPaginator;

	public searchKey!: string;
	public isLoading: boolean = true;

	public openEditDialog(data: any): void {

	}

	public openDialog(id: number): void {
		// Opens the dialog box for confirmation
		const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
			data: {
				message: 'Are you sure want to delete this building?',
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

					this.buildingService.deleteBuilding(id).subscribe({
						next: (res) => {
							this.isLoading = false;
							let apiResponse: ApiResponseModel = res;

							this.toastService.showSuccessToast(apiResponse.message);
							this.getBuildings();
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

	public getBuildings(): void {
		this.buildingService.getBuildings().subscribe({
			next: (res) => {
				this.isLoading = false;
				let apiResponse: ApiResponseModel = res;
				let buildingsRes: any = apiResponse.data;

				this.listData = new MatTableDataSource(buildingsRes);
				this.listData.sort = this.sort;
				this.listData.paginator = this.paginator;
			},
			error: (err) => {
				this.isLoading = false;
				let responseData: ApiResponseModel = err.error;

				this.toastService.showFailToast(responseData.message);
			}
		});
	}
}
