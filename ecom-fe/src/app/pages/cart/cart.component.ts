import { Component } from '@angular/core';

@Component({
  selector: 'app-cart',
  template: `
    <h1>Cart</h1>
    <p>Здесь будет список товаров в корзине.</p>
  `,
  styles: [`
    h1 { margin-bottom: 1rem; }
    p { color: #555; }
  `]
})
export class CartComponent {}
