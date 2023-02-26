import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { User } from '@lib/interfaces';
import { AuthService, ProductService } from '@lib/services';
import { CategoryService } from '@lib/services/category/category.service';

@Component({
  selector: 'category-item',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './category-item.component.html',
  styleUrls: ['./category-item.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CategoryItemComponent {

  @Input("category") category: {
    id: number,
    nombre: string,
    descripcion: string,
  };

  @Output("onUpdate") onUpdate = new EventEmitter();

  categoryForm = this.formBuilder.group({
    nombre: '',
    descripcion: '',
  });

  isEditing = false;

  constructor(
    private _categoryService: CategoryService,
    private _productService: ProductService,
    private formBuilder: FormBuilder,
  ) {}

  ngOnInit(): void {
  }

  editCategory() {
    this.isEditing = !this.isEditing;
    if (this.isEditing) {
      this.initForm();
    }
  }

  saveCategory() {
    this._categoryService.updateCategory({
      id: this.category.id,
      nombre: this.categoryForm.value.nombre,
      descripcion: this.categoryForm.value.descripcion
    }).subscribe(
      (category: any) => {
        this.onUpdate.emit(category);
      },
      (error) => {
        console.error(error);
      }
    )
  }

  deleteCategory() {
    this._categoryService.deleteCategory(this.category.id).subscribe(
      (category: any) => {
        this.onUpdate.emit(category);
      },
      (error) => {
        console.error(error);
      }
    )
  }

  initForm() {
    this.categoryForm = this.formBuilder.group({
      nombre: this.category.nombre,
      descripcion: this.category.descripcion,
    });
  }
}
