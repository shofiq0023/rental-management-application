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

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "login", component: LoginComponent },
  { path: "signup", component: SignupComponent },
  { path: "dashboard", component: DashboardComponent },
  { path: "blogs-list", component: BlogListComponent },
  { path: "create-blog", component: BlogAddComponent },
  { path: "building-list", component: BuildingListComponent },
  { path: "add-building", component: BuildingAddComponent },
  { path: "users-list", component: UsersListComponent },
  { path: "add-user", component: UsersAddComponent },
  { path: "renter-list", component: RentersListComponent },
  { path: "add-renter", component: RentersAddComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
