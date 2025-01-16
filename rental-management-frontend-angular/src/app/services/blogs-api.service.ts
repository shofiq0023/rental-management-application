import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { BlogResponseModel } from '../models/response/blog-response.model';
import { BlogModel } from '../models/data-models/blog.model';
import { api } from '../api-list';

@Injectable({
  providedIn: 'root'
})
export class BlogsApiService {
  constructor(private http: HttpClient) { }

  // Calls the API to get the Blog list response
  public getBlogs(): Observable<BlogResponseModel> {
    return this.http.get<BlogResponseModel>(api.BLOG_LIST);
  }

  // call the API to save a new Blog
  public addBlog(body: BlogModel): Observable<boolean> {
    let success = false;

    this.http.post(api.BLOG_ADD, body).subscribe({
      next: (res) => {
        success = true;
      },
      error: (err) => {
        console.log(err);
      }
    });

    return of(success);
  }

  // call the API to delete a Blog
  public deleteBlog(id: string): Observable<number> {
    let url = api.BLOG_DELETE + id;
    return this.http.delete<number>(url);
  }

  // call the API to update a blog
  public updateBlog(updatedBlog: BlogModel): Observable<boolean> {
    let success = false;

    this.http.put(api.BLOG_UPDATE + updatedBlog._id, updatedBlog).subscribe({
      next: (res) => success = true,
      error: (err) => console.log(err)
    })

    return of(success);
  }
}
