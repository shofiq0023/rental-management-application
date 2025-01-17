import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar-links',
  templateUrl: './navbar-links.component.html',
  styleUrls: ['./navbar-links.component.scss']
})
export class NavbarLinksComponent {
  public links: any[] = [
    {
      name: "Dashboard",
      routerLink: "dashboard",
      listIcon: "dashboard"
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
}
