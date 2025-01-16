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
      name: "Property",
      routerLink: "property",
      listIcon: "apartment"
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
      routerLink: "users-list",
      listIcon: "group"
    },
    {
      name: "Add Renter",
      routerLink: "add-user",
      listIcon: "person_add"
    }
  ];
}
