import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CategoryItemComponent } from '@lib/components';
import { CategoryService } from '@lib/services/category/category.service';

@Component({
  standalone: true,
  imports: [CommonModule, CategoryItemComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './categories.page.html',
  styleUrls: ['./categories.page.css'],
})
export class CategoriesPage implements OnInit {
  categories: any[] = [];
  isCreatingCategory = false;

  categoryForm = this.formBuilder.group({
    nombre: '',
    descripcion: ''
  });

  constructor(
    private _categoryService: CategoryService,
    private formBuilder: FormBuilder,
  ) {
  }

  ngOnInit() {
    this.loadCategories();
  }

  loadCategories() {
    this._categoryService.getCategories().subscribe(
      (categories: any) => {
        this.categories = categories;
      },
      (error) => {
        console.error(error);
        this.categories = [];
      }
    )
  }

  openCategoryForm() {
    this.isCreatingCategory = !this.isCreatingCategory;
    if (this.isCreatingCategory) {
      this.initForm();
    }
  }

  createCategory() {
    this._categoryService.createCategory(this.categoryForm.value).subscribe(
      () => {
        this.loadCategories();
        this.isCreatingCategory = false;
      },
      (error) => {
        console.error(error);
      }
    )
  }

  initForm(){
    this.categoryForm = this.formBuilder.group({
      nombre: '',
      descripcion: ''
    });
  }
}
