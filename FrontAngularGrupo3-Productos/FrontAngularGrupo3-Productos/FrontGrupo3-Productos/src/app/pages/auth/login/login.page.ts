import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AuthService } from '@lib/services';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.css'],
})
export class LoginPage {
  private _callbackURL: string;

  loginForm = this.formBuilder.group({
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

  onSubmitSignIn() {
    this._authService.login({ user: this.loginForm.value.user, password: this.loginForm.value.password }, this._callbackURL);
  }
}
