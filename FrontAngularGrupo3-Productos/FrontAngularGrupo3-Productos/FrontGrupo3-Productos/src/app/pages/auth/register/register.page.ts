import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { AuthService } from '@lib/services';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.css'],
})
export class RegisterPage {
  private _callbackURL: string;

  signupForm = this.formBuilder.group({
    user: '',
    password: ''
  });

  constructor(
    private _activatedRoute: ActivatedRoute,
    private _authService: AuthService,
    private formBuilder: FormBuilder,
  ) {
    this._callbackURL = this._activatedRoute.snapshot.queryParamMap.get('callbackURL') || `/`;
  }

  onSubmitSignUp() {
    this._authService.register({ username: this.signupForm.value.user, password: this.signupForm.value.password }).subscribe(
      (user: any) => {
        this._authService.login({ user: this.signupForm.value.user, password: this.signupForm.value.password }, this._callbackURL);
      },
      (error) => {
        console.error(error);
      }
    );;
  }
}
