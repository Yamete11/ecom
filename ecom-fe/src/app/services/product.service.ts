import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Product {
  id: number;
  title: string;
  price: number;
  category: string;
}

@Injectable({ providedIn: 'root' })
export class ProductService {
  private http = inject(HttpClient);

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('http://localhost:8080/api/products', { withCredentials: true });
  }

  addProductToCart(productId: number): void {
    this.http.post('http://localhost:8080/api/products/add-to-cart', productId, {
      withCredentials: true,
      responseType: 'text'
    }).subscribe();
  }
}
