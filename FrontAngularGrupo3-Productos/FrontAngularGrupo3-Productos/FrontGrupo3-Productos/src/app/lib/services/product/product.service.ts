import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Router } from "@angular/router";
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

    constructor(
        public router: Router,
        public http: HttpClient,
    ) {

    }

    getProducts(category?: number, options?: any) {
      let url = `${environment.apiUrl}/api/products`;
      if (category) {
        url = `${environment.apiUrl}/api/categories/${category}/product`;
      }
      const request = this.http.get(url, { params: options });
      return request;
    }

    createProduct(category: number, product: any) {
      const url = `${environment.apiUrl}/api/categories/${category}/product`;
      const request = this.http.post(url, product);
      return request;
    }

    updateProduct(product: any) {
      const url = `${environment.apiUrl}/api/products`;
      const request = this.http.put(url, product);
      return request;
    }

    deleteProduct(id: number) {
      const url = `${environment.apiUrl}/api/products/${id}`;
      const request = this.http.delete(url);
      return request;
    }

    deleteProductsFromCategory(id: number) {
      const url = `${environment.apiUrl}/api/categories/${id}/products`;
      const request = this.http.delete(url);
      return request;
    }

    getFavoriteProducts() {
      let url = `${environment.apiUrl}/api/productos/favoritos`;
      const request = this.http.get(url);
      return request;
    }

    favoriteProduct(favoriteProduct) {
      let url = `${environment.apiUrl}/api/productos/favoritos`;
      const request = this.http.post(url, favoriteProduct);
      return request;
    }

    unfavoriteProduct(favoriteProductId: number) {
      let url = `${environment.apiUrl}/api/productos/favoritos/${favoriteProductId}`;
      const request = this.http.delete(url);
      return request;
    }
}