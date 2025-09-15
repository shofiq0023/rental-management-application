import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.scss']
})
export class ConfirmationDialogComponent {
  
  constructor(
    // For injecting 'data' object to this component
    @Inject(MAT_DIALOG_DATA) private data: any,

    private dialogRef: MatDialogRef<ConfirmationDialogComponent>) {
    if (data) {
      this.message = data.message || this.message;

      if (data.buttonText) {
        this.confirmButtonText = data.buttonText.ok || this.confirmButtonText;
        this.cancelButtonText = data.buttonText.cancel || this.cancelButtonText;
      }
    }
  }

  // Default text
  public message: string = "Are you sure?"
  public confirmButtonText = "Yes"
  public cancelButtonText = "Cancel"

  public onConfirmClick(): void {
    this.dialogRef.close(true);
  }
}
