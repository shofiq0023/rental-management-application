import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
	providedIn: 'root'
})
export class ToastService {

	constructor(private snackBar: MatSnackBar) { }

	public showSuccessToast(message: string, duration: number = 3000) {
		this.snackBar.open(message, "", {
			duration: duration,
			panelClass: ["snack-success"]
		});
	}

	public showFailToast(message: string, duration: number = 3000) {
		this.snackBar.open(message, "", {
			duration: duration,
			panelClass: ["snack-fail"]
		});
	}
}
