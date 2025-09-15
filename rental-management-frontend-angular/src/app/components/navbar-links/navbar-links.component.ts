import { Component } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
	selector: 'app-navbar-links',
	templateUrl: './navbar-links.component.html',
	styleUrls: ['./navbar-links.component.scss']
})
export class NavbarLinksComponent {
	private ROLE_USER: string = 'ROLE_USER';
	private ROLE_ADMIN: string = 'ROLE_ADMIN';

	constructor(private authService: AuthenticationService){
		this.userType = this.ROLE_USER;
	}
	
	public userType: string;

	public ngOnInit(): void {
		this.authService.getUserType().subscribe({
			next: (userType) => {
				this.userType = userType;
			}
		});
	}

	public isAdminUser() {
		return this.userType == this.ROLE_ADMIN;
	}

	public isRenterUser() {
		return this.userType == this.ROLE_USER;
	}

	public links: any[] = [
		{
			name: "Dashboard",
			routerLink: "dashboard",
			listIcon: "dashboard"
		},
		{
			name: "Account Settings",
			routerLink: "account-settings",
			listIcon: "settings"
		},
	]

	public propertyRoutingLinks: any[] = [
		{
			name: "Building List",
			routerLink: "building-list",
			listIcon: "domain"
		},
		{
			name: "Add new building",
			routerLink: "add-building",
			listIcon: "domain_add"
		},
	];

	public userRoutingLinks: any[] = [
		{
			name: "User List",
			routerLink: "users-list",
			listIcon: "group"
		},
		{
			name: "Add user",
			routerLink: "add-user",
			listIcon: "person_add"
		},
		{
			name: "Approve Users",
			routerLink: "approve-user",
			listIcon: "manage_accounts"
		}
	];

	public rentersLink: any[] = [
		{
			name: "Renter List",
			routerLink: "renter-list",
			listIcon: "account_box"
		},
		{
			name: "Add Renter",
			routerLink: "add-renter",
			listIcon: "group_add"
		}
	];

	public rentPaymentRoutingLink: any[] = [
		{
			name: "Payment History",
			routerLink: "rent-payment-history",
			listIcon: "receipt_long"
		},
		{
			name: "Pay Rent",
			routerLink: "rent-payment",
			listIcon: "payments"
		},
	];

	public renterRentPaymentLinks: any[] = [
		{
			name: "Payment History",
			routerLink: "renter-rent-payment-history",
			listIcon: "receipt_long"
		},
		{
			name: "Pay Rent",
			routerLink: "renter-rent-payment",
			listIcon: "payments"
		},
	];
}
