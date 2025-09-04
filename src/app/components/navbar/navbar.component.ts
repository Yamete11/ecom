import { Component, inject } from '@angular/core';
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  template: `
    @if (auth.isLoggedIn()) {
      <nav class="navbar">
        <button routerLink="/products" routerLinkActive="active">Products</button>
        <button routerLink="/cart" routerLinkActive="active">Cart</button>
        <button routerLink="/orders" routerLinkActive="active">Orders</button>
        <button (click)="logout()">Logout</button>
      </nav>
    }
  `,
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  styles: [`
    .navbar {
      display: flex;
      gap: 1rem;
      padding: 1rem;
      background: #eee;
    }

    .active {
      font-weight: bold;
    }
  `]
})
export class NavbarComponent {
  auth = inject(AuthService);
  router = inject(Router);

  logout() {
    this.auth.logout().subscribe();
  }
}
