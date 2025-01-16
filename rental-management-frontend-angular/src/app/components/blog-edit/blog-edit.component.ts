import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BlogModel } from 'src/app/models/data-models/blog.model';
import { BlogsApiService } from 'src/app/services/blogs-api.service';


@Component({
  selector: 'app-blog-edit',
  templateUrl: './blog-edit.component.html',
  styleUrls: ['./blog-edit.component.scss']
})
export class BlogEditComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: BlogModel, private blogsApiService: BlogsApiService, private snackBar: MatSnackBar) { }

  public submitBtnDisabled: boolean = false;

  public updateBlog(updatedBlog: BlogModel): void {
    this.submitBtnDisabled = true;

    if (updatedBlog.__v != undefined) {
      updatedBlog.__v + 1;
    }

    updatedBlog.updatedAt = new Date();

    this.blogsApiService.updateBlog(updatedBlog).subscribe({
      next: () => {
        this.snackBar.open("Blog updated", "", {
          duration: 3000,
          panelClass: ["snack-success"]
        });
        this.submitBtnDisabled = false;
      },
      error: (err) => {
        console.error(err);
        this.snackBar.open("There was an error", "", {
          duration: 3000,
          panelClass: ["snack-fail"]
        });
        this.submitBtnDisabled = false;
      }
    })

    console.log(updatedBlog);
  }

}
