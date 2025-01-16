import { Component, ViewChild, HostListener } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { LocalStorageService } from './services/local-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  public title: string = 'blog-website-angular';
  public showFiller: boolean = false;
  public screenWidth!: number;

  private screenWidth$ = new BehaviorSubject<number>(window.innerWidth);
  @ViewChild('sidenav') sidenav!: MatSidenav;
  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.screenWidth$.next(event.target.innerWidth);
  }

  public constructor(private router: Router, private localStorageService: LocalStorageService) { }

  ngOnInit() {
    this.screenWidth$.subscribe(width => {
      this.screenWidth = width;
    });
  }

  public logout(): void {
    this.localStorageService.clear();
    this.router.navigate(["/login"]);
  }

  private getRoute(): string {
    return this.router.url;
  }

  private getRouteName(): string {
    return this.getRoute();
  }

  public isAuthenticating(): boolean {
    return this.getRouteName() == '/login' || this.getRouteName() == '/signup';
  }

}
