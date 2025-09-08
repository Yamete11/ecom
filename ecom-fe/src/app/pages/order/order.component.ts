import { Component } from '@angular/core';

@Component({
  selector: 'app-orders',
  template: `
    <h1>Orders</h1>
    <p>Здесь будут ваши заказы.</p>
  `,
  styles: [`
    h1 { margin-bottom: 1rem; }
    p { color: #555; }
  `]
})
export class OrdersComponent {}
