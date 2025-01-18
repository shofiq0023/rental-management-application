import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BlogListComponent } from './components/blog-list/blog-list.component';
import { BlogAddComponent } from './components/blog-add/blog-add.component';
import { NavbarLinksComponent } from './components/navbar-links/navbar-links.component';
import { MatListModule } from '@angular/material/list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatSortModule } from '@angular/material/sort';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { MatSelectModule } from '@angular/material/select';
import { BlogEditComponent } from './components/blog-edit/blog-edit.component';
import { SignupComponent } from './components/signup/signup.component';
import { NgxBootstrapIconsModule } from 'ngx-bootstrap-icons';
import { BuildingListComponent } from './components/building-list/building-list.component';
import { BuildingAddComponent } from './components/building-add/building-add.component';
import { UsersListComponent } from './components/users-list/users-list.component';
import { UsersAddComponent } from './components/users-add/users-add.component';
import { RentersListComponent } from './components/renters-list/renters-list.component';
import { RentersAddComponent } from './components/renters-add/renters-add.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { UsersApproveComponent } from './components/users-approve/users-approve.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    BlogListComponent,
    BlogAddComponent,
    NavbarLinksComponent,
    ConfirmationDialogComponent,
    BlogEditComponent,
    SignupComponent,
    BuildingListComponent,
    BuildingAddComponent,
    UsersListComponent,
    UsersAddComponent,
    RentersListComponent,
    RentersAddComponent,
    UsersApproveComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    MatSidenavModule,
    MatGridListModule,
    MatToolbarModule,
    MatIconModule,
    MatBadgeModule,
    MatButtonModule,
    MatDialogModule,
    MatProgressBarModule,
    MatSnackBarModule,
    MatTableModule,
    MatListModule,
    MatExpansionModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    NgxBootstrapIconsModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
