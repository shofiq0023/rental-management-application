import { Component } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  public blogTitle!: string;
  public blogThumbnailUrl!: string;
  public blogDesc!: string;
  public submitBtnDisabled = false;

  addBlog() {}
}
