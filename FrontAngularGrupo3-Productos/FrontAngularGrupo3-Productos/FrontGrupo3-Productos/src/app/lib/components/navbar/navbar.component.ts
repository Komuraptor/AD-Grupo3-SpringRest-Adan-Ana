import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { User } from '@lib/interfaces';
import { AuthService } from '@lib/services';
import { LogoComponent } from '../logo/logo.component';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule, LogoComponent],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavbarComponent implements OnInit {
  userInfo: User;

  constructor(private _router: Router, private _authService: AuthService) {}

  ngOnInit(): void {
    if (this._authService.isLoggedIn) {
      this.userInfo = this._authService.getUserInfo();
    }
  }

  onClickSignOut(): void {
    this._authService.logout();
    this._router.navigateByUrl('/auth/login');
  }

  editCategories(): void {
    this._router.navigateByUrl('/categories');
  }
}
