import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css'],
})
export class FooterComponent implements OnInit {
  items: MenuItem[] = [
    {
      label: 'Medicamentos',
      icon: 'pi pi-fw pi-home',
      routerLink: '/medication',
      routerLinkActiveOptions: { exact: true },
    },
    {
      label: 'Sales',
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
