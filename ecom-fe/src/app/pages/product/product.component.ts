import { Component, signal, OnInit, ChangeDetectionStrategy, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService, Product } from '../../services/product.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="home-container">
      <h1>Welcome to the PRODUCT Page!</h1>
      <ul>
        @for ( p of products() ; track p.id) {
          <li>
            {{p.title}} - {{p.price}}$ - {{p.category}}
          </li>
        }
      </ul>
    </div>
  `,
  styles: [`
    .home-container {
      max-width: 600px;
      margin: auto;
      padding: 2rem;
      text-align: center;
    }
    ul {
      list-style: none;
      padding: 0;
    }
    li {
      margin-bottom: 0.5rem;
    }
  `],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductComponent implements OnInit {
  private productService = inject(ProductService);

  products = signal<Product[]>([]);

  ngOnInit() {
    this.productService.getAllProducts().subscribe({
      next: data => {
        this.products.set(data);
        console.log('Products loaded:', this.products());
      },
      error: err => console.error(err)
    });
  }


}
