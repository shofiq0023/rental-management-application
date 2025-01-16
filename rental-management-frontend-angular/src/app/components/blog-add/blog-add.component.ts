import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BlogModel } from 'src/app/models/data-models/blog.model';
import { BlogsApiService } from 'src/app/services/blogs-api.service';

@Component({
  selector: 'app-blog-add',
  templateUrl: './blog-add.component.html',
  styleUrls: ['./blog-add.component.scss']
})
export class BlogAddComponent {
  public constructor(private blogsApiService: BlogsApiService, private snackBar: MatSnackBar) { }

  public blogTitle!: string;
  public blogThumbnailUrl!: string;
  public blogDesc!: string;
  public submitBtnDisabled = false;


  // clear form data
  private clearForm() {
    this.blogTitle = "";
    this.blogDesc = "";
    this.blogThumbnailUrl = "";
  }

  // creates a new Blog
  public addBlog() {
    this.submitBtnDisabled = true;
    let body: BlogModel = {
      title: this.blogTitle,
      thumbnail: this.blogThumbnailUrl,
      description: this.blogDesc,
      like: 0,
      view: 0,
      love: 0,
    }

    this.blogsApiService.addBlog(body).subscribe({
      next: () => {
        this.clearForm();
        this.submitBtnDisabled = false;
        this.snackBar.open("Created a new Blog", "", {
          duration: 3000,
          panelClass: ["snack-success"]
        });
      },
      error: () => {
        console.error("There was an error");
        this.snackBar.open("There was an error", "", {
          duration: 3000,
          panelClass: ["snack-fail"]
        });
        this.clearForm();
      }
    });
  }
}
