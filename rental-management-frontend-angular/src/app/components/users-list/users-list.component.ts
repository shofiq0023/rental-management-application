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
import { AuthenticationService } from 'src/app/services/authentication.service';
import { UserModel } from 'src/app/models/data-models/user.model';

@Component({
	selector: 'app-users-list',
	templateUrl: './users-list.component.html',
	styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent {
	constructor(private toastService: ToastService, public dialog: MatDialog, private userAuthenticationService: AuthenticationService) { }

	public ngOnInit(): void {
		this.getUsers();
	}

	public displayedColumns: string[] = ['num', 'name', 'username', 'email', 'phone', 'userType', 'address', 'signupDate', 'actions'];
	public listData!: MatTableDataSource<UserModel>;

	@ViewChild(MatSort)
	private sort!: MatSort;

	@ViewChild(MatPaginator)
	private paginator!: MatPaginator;

	public searchKey!: string;
	public isLoading: boolean = true;

	public openEditDialog(data: any): void {

	}

	public openDialog(userId: number): void {
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

					this.userAuthenticationService.deleteUser(userId).subscribe({
						next: (res) => {
							this.isLoading = false;
							let apiResponse: ApiResponseModel = res;

							this.toastService.showSuccessToast(apiResponse.message);
							this.getUsers(); 
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

	public getUsers(): void {
		this.userAuthenticationService.getAllUser().subscribe({
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

	public getLoggedInUserId(): number {
		return this.userAuthenticationService.getUserId();
	}
}
