import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BlogListComponent } from './components/blog-list/blog-list.component';
import { BlogAddComponent } from './components/blog-add/blog-add.component';
import { SignupComponent } from './components/signup/signup.component';
import { BuildingListComponent } from './components/building-list/building-list.component';
import { BuildingAddComponent } from './components/building-add/building-add.component';
import { UsersListComponent } from './components/users-list/users-list.component';
import { UsersAddComponent } from './components/users-add/users-add.component';
import { RentersListComponent } from './components/renters-list/renters-list.component';
import { RentersAddComponent } from './components/renters-add/renters-add.component';
import { authGuard } from './guards/auth.guard';
import { loginGuard } from './guards/login.guard';

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "login", component: LoginComponent, canActivate: [loginGuard] },
  { path: "signup", component: SignupComponent, canActivate: [loginGuard] },
  { path: "dashboard", component: DashboardComponent, canActivate: [authGuard] },
  { path: "blogs-list", component: BlogListComponent, canActivate: [authGuard] },
  { path: "create-blog", component: BlogAddComponent, canActivate: [authGuard] },
  { path: "building-list", component: BuildingListComponent, canActivate: [authGuard] },
  { path: "add-building", component: BuildingAddComponent, canActivate: [authGuard] },
  { path: "users-list", component: UsersListComponent, canActivate: [authGuard] },
  { path: "add-user", component: UsersAddComponent, canActivate: [authGuard] },
  { path: "renter-list", component: RentersListComponent, canActivate: [authGuard] },
  { path: "add-renter", component: RentersAddComponent, canActivate: [authGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
