import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Router } from "@angular/router";
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

    constructor(
        public router: Router,
        public http: HttpClient,
    ) {

    }

    getCategories(options?: any) {
      const url = `${environment.apiUrl}/api/categories`;
      const request = this.http.get(url, { params: options });
      return request;
    }

    createCategory(category: any) {
      const url = `${environment.apiUrl}/api/categories`;
      const request = this.http.post(url, category);
      return request;
    }

    updateCategory(category: any) {
      const url = `${environment.apiUrl}/api/categories`;
      const request = this.http.put(url, category);
      return request;
    }

    deleteCategory(id: number) {
      const url = `${environment.apiUrl}/api/categories/${id}`;
      const request = this.http.delete(url);
      return request;
    }
}