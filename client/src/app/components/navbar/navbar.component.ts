import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  items: MenuItem[] = [
    {
      label: 'Medicamentos',
      icon: 'pi pi-fw pi-home',
      routerLink: '/medication',
      routerLinkActiveOptions: { exact: true },
    },
    {
      label: 'Ventas',
      icon: 'pi pi-fw pi-calendar',
      routerLink: '/sales',
      routerLinkActiveOptions: { exact: true },
    },
  ];

  activeItem: MenuItem = this.items[0];

  ngOnInit() {
    this.activeItem = this.items[0];
  }

  constructor() {}
}
