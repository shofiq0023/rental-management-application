import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SignupComponent } from './components/signup/signup.component';
import { BuildingListComponent } from './components/building-list/building-list.component';
import { BuildingAddComponent } from './components/building-add/building-add.component';
import { UsersListComponent } from './components/users-list/users-list.component';
import { UsersAddComponent } from './components/users-add/users-add.component';
import { RentersListComponent } from './components/renters-list/renters-list.component';
import { RentersAddComponent } from './components/renters-add/renters-add.component';
import { authGuard } from './guards/auth.guard';
import { loginGuard } from './guards/login.guard';
import { UsersApproveComponent } from './components/users-approve/users-approve.component';
import { AccountSettingsComponent } from './components/account-settings/account-settings.component';
import { RentPaymentHistoryComponent } from './components/rent-payment-history/rent-payment-history.component';
import { RenterRentPaymentHistoryComponent } from './components/renter-rent-payment-history/renter-rent-payment-history.component';
import { RentPaymentAddComponent } from './components/rent-payment-add/rent-payment-add.component';
import { RenterRentPaymentAddComponent } from './components/renter-rent-payment-add/renter-rent-payment-add.component';
import { rentersGuard } from './guards/renters.guard';
import { adminGuard } from './guards/admin.guard';

const routes: Routes = [
	{ path: "", redirectTo: "login", pathMatch: "full" },
	{ path: "login", component: LoginComponent, canActivate: [loginGuard] },
	{ path: "signup", component: SignupComponent, canActivate: [loginGuard] },

	{ path: "dashboard", component: DashboardComponent, canActivate: [authGuard] },
	{ path: "account-settings", component: AccountSettingsComponent, canActivate: [authGuard] },

	{ path: "building-list", component: BuildingListComponent, canActivate: [authGuard, adminGuard] },
	{ path: "add-building", component: BuildingAddComponent, canActivate: [authGuard, adminGuard] },

	{ path: "users-list", component: UsersListComponent, canActivate: [authGuard, adminGuard] },
	{ path: "add-user", component: UsersAddComponent, canActivate: [authGuard, adminGuard] },
	{ path: "approve-user", component: UsersApproveComponent, canActivate: [authGuard, adminGuard] },

	{ path: "renter-list", component: RentersListComponent, canActivate: [authGuard, adminGuard] },
	{ path: "add-renter", component: RentersAddComponent, canActivate: [authGuard, adminGuard] },

	{ path: "rent-payment-history", component: RentPaymentHistoryComponent, canActivate: [authGuard, adminGuard] },
	{ path: "rent-payment", component: RentPaymentAddComponent, canActivate: [authGuard, adminGuard] },

	{ path: "renter-rent-payment-history", component: RenterRentPaymentHistoryComponent, canActivate: [authGuard, rentersGuard] },
	{ path: "renter-rent-payment", component: RenterRentPaymentAddComponent, canActivate: [authGuard, rentersGuard] },
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
