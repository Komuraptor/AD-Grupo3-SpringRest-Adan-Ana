import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { User } from '@lib/interfaces';
import { AuthService, ProductService } from '@lib/services';

@Component({
  selector: 'product-item',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductItemComponent {

  @Input("product") product: {
    id: number,
    nombre: string,
    descripcion: string,
    precio: number,
    idCategoria: number,
    category: any,
    isFavoriteId: number,
  };

  @Input("categories") categories: any[];

  @Output("onUpdate") onUpdate = new EventEmitter();

  productForm = this.formBuilder.group({
    nombre: '',
    descripcion: '',
    precio: 0,
    idCategoria: 0,
  });

  userInfo: User;
  isAdmin = false;

  isEditing = false;

  constructor(
    private _authService: AuthService,
    private _productService: ProductService,
    private formBuilder: FormBuilder,
  ) {}

  ngOnInit(): void {
    if (this._authService.isLoggedIn) {
      this.userInfo = this._authService.getUserInfo();
      this.isAdmin = this.userInfo && this.userInfo.role === 'ROLE_ADMIN';
    }
  }

  saveBookmark() {
    this._productService.favoriteProduct({
      idUser: this.userInfo.id,
      idProducto: this.product.id,
    }).subscribe(
      (product: any) => {
        this.onUpdate.emit(product);
      },
      (error) => {
        console.error(error);
      }
    )
  }

  removeBookmark() {
    this._productService.unfavoriteProduct(this.product.isFavoriteId).subscribe(
      (product: any) => {
        this.onUpdate.emit(product);
      },
      (error) => {
        console.error(error);
      }
    )
  }

  editProduct() {
    this.isEditing = !this.isEditing;
    if (this.isEditing) {
      this.initForm();
    }
  }

  saveProduct() {
    this._productService.updateProduct({
      id: this.product.id,
      nombre: this.productForm.value.nombre,
      descripcion: this.productForm.value.descripcion,
      precio: this.productForm.value.precio,
      idCategoria: this.productForm.value.idCategoria
    }).subscribe(
      (product: any) => {
        this.onUpdate.emit(product);
      },
      (error) => {
        console.error(error);
      }
    )
  }

  deleteProduct() {
    this._productService.deleteProduct(this.product.id).subscribe(
      (product: any) => {
        this.onUpdate.emit(product);
      },
      (error) => {
        console.error(error);
      }
    )
  }

  initForm() {
    this.productForm = this.formBuilder.group({
      nombre: this.product.nombre,
      descripcion: this.product.descripcion,
      precio: this.product.precio,
      idCategoria: this.product.idCategoria,
    });
  }
}
