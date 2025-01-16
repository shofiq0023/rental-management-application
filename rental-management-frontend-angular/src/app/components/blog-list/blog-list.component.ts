import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { BlogModel } from 'src/app/models/data-models/blog.model';
import { BlogsApiService } from 'src/app/services/blogs-api.service';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BlogEditComponent } from '../blog-edit/blog-edit.component';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.scss']
})
export class BlogListComponent implements OnInit {
  constructor(private blogsApiService: BlogsApiService, public dialog: MatDialog, private snackBar: MatSnackBar) { }

  public ngOnInit(): void {
    this.getBlogs();
  }

  public displayedColumns: string[] = ['num', 'thumbnail', 'title', 'views', 'like', 'love', 'createdAt', 'actions'];
  public listData!: MatTableDataSource<BlogModel>;

  private defaultImage = "/assets/default-image.png";

  @ViewChild(MatSort)
  private sort!: MatSort;

  @ViewChild(MatPaginator)
  private paginator!: MatPaginator;

  public searchKey!: string;
  public loading: boolean = true;


  // METHODS =================================
  public getBlogs(): void {
    this.blogsApiService.getBlogs().subscribe({
      next: (res) => {
        this.listData = new MatTableDataSource(res.data);
        this.listData.sort = this.sort;
        this.listData.paginator = this.paginator;
        this.loading = false;
      },
      error: (err) => console.log(err)
    });
  }

  public onSearchClear(): void {
    this.searchKey = "";
    this.applyFilter();
  }

  public applyFilter(): void {
    this.listData.filter = this.searchKey?.trim().toLowerCase();
  }

  // Sets the default image of a Blog
  public setDefaultImage(event: Event) {
    const imgElement = event.target as HTMLImageElement;
    imgElement.src = this.defaultImage;
  }

  public openDialog(id: string): void {
    // Opens the dialog box for confirmation
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        message: 'Are you sure want to delete?',
        buttonText: {
          ok: 'Yes',
          cancel: 'No'
        }
      }
    });

    // Delete api call after confirm button is pressed
    dialogRef.afterClosed().subscribe({
      next: (confirmed: boolean) => {
        if (confirmed) {
          this.loading = true;
          this.blogsApiService.deleteBlog(id).subscribe(() => {
            this.getBlogs();
            this.snackBar.open("Blog was deleted", "", {
              duration: 3000,
              panelClass: 'snack-success'
            });
          });
        }
      }
    });
  }

  public openEditDialog(blogRow: BlogModel): void {
    const dialogRef = this.dialog.open(BlogEditComponent, { data: blogRow });
  }
}
