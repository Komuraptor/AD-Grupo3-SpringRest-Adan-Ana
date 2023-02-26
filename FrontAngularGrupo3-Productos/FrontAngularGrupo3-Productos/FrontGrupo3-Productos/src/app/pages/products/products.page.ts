import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductItemComponent } from '@lib/components';
import { User } from '@lib/interfaces';
import { AuthService, ProductService } from '@lib/services';
import { CategoryService } from '@lib/services/category/category.service';

@Component({
  standalone: true,
  imports: [CommonModule, ProductItemComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './products.page.html',
  styleUrls: ['./products.page.css'],
})
export class ProductsPage implements OnInit {
  categories: any[] = [];
  favorites: any[] = [];
  products: any[] = [];
  selectedIdCategoria: string = '0';
  isCreatingProduct = false;
  onlyFavorites = false;

  productForm = this.formBuilder.group({
    nombre: '',
    descripcion: '',
    precio: 0,
    idCategoria: 0
  });

  userInfo: User;
  isAdmin = false;

  constructor(
    private _authService: AuthService,
    private _productService: ProductService,
    private _categoryService: CategoryService,
    private formBuilder: FormBuilder,
  ) {
  }

  ngOnInit() {
    if (this._authService.isLoggedIn) {
      this.userInfo = this._authService.getUserInfo();
      this.isAdmin = this.userInfo && this.userInfo.role === 'ROLE_ADMIN';
    }
    this.loadCategories();
  }

  loadCategories() {
    this._categoryService.getCategories().subscribe(
      (categories: any) => {
        this.categories = categories;
        this.loadFavorites();
      },
      (error) => {
        console.error(error);
        this.categories = [];
      }
    )
  }

  loadFavorites() {
    this._productService.getFavoriteProducts().subscribe(
      (favoritos: any) => {
        this.favorites = favoritos;
        this.loadProducts();
      },
      () => {
        this.favorites = [];
        this.loadProducts();
      }
    )
  }

  loadProducts() {
    let idCategoria = Number(this.selectedIdCategoria);
    this._productService.getProducts(idCategoria).subscribe(
      (products: any) => {
        this.products = this.mapProductsExtraInfo(products);
      },
      (error) => {
        console.error(error);
        this.products = [];
      }
    )
  }

  mapProductsExtraInfo(products) {
    for(let i = 0; i < products.length; i++) {
      products[i].category = this.categories.find(x => x.id === products[i].idCategoria);
      const isFavorite = this.favorites.find(x => x.idProducto === products[i].id);
      products[i].isFavoriteId = isFavorite ? isFavorite.id : null;
    }
    return products;
  }

  openProductForm() {
    this.isCreatingProduct = !this.isCreatingProduct;
    if (this.isCreatingProduct) {
      this.initForm();
    }
  }

  createProduct() {
    this._productService.createProduct(this.productForm.value.idCategoria, this.productForm.value).subscribe(
      () => {
        this.loadFavorites();
        this.isCreatingProduct = false;
      },
      (error) => {
        console.error(error);
      }
    )
  }

  initForm(){
    this.productForm = this.formBuilder.group({
      nombre: '',
      descripcion: '',
      precio: 0,
      idCategoria: 0
    });
  }

  changeOnlyFavorites() {
    this.onlyFavorites = !this.onlyFavorites;
  }
}
