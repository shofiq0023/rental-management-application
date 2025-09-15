import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faMinus, faPlus } from '@fortawesome/free-solid-svg-icons';
import { FlatsModel } from 'src/app/models/data-models/flats.model';
import { ApiResponseModel } from 'src/app/models/response/api-response.model';
import { BuildingService } from 'src/app/services/building.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
	selector: 'app-building-add',
	templateUrl: './building-add.component.html',
	styleUrls: ['./building-add.component.scss']
})
export class BuildingAddComponent {
	public constructor(private buildingService: BuildingService, private toastService: ToastService, private router: Router) { }
	public faMinus: IconDefinition = faMinus;
	public faAdd: IconDefinition = faPlus;

	public isLoading: boolean = false;

	public bulidingName: string = '';
	public address: string = '';
	public flats: FlatsModel[] = [];

	public ngOnInit(): void {
		this.addEmptyFlatInputBox();
	}

	public addEmptyFlatInputBox(): void {
		let emptyFlatModel: FlatsModel = { flatNo: '' };
		this.flats.push(emptyFlatModel);
	}

	public removeFlatInput(flatInputBoxIndex: number): void {
		this.flats.splice(flatInputBoxIndex, 1);
	}

	public addBuilding(): void {
		this.isLoading = true;

		let buildingsReqObj = {
			name: this.bulidingName,
			address: this.address,
			flats: this.flats
		}

		this.buildingService.addBuilding(buildingsReqObj).subscribe({
			next: (res) => {
				this.isLoading = false;
				let apiResponse: ApiResponseModel = res;

				this.toastService.showSuccessToast(apiResponse.message);
				this.router.navigate(['/building-list']);
			},
			error: (err) => {
				this.isLoading = false;
				let responseData: ApiResponseModel = err.error;

				this.toastService.showFailToast(responseData.message);
			}
		});
	}

}
