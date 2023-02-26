import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Router } from "@angular/router";
import { environment } from '@env/environment';
import { storage } from '@lib/utils/storage/storage.utils';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '@lib/interfaces';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(
      public router: Router,
      public http: HttpClient
  ) {

  }
  isLoggedIn$ = new BehaviorSubject<boolean>(!!storage.getItem('App/session'));

  get isLoggedIn(): boolean {
    return this.isLoggedIn$.getValue();
  }

  register(registerInfo: any): Observable<Object> {
    const url = `${environment.apiUrl}/register`;
    return this.http.post(url, registerInfo);
  }

  login(loginInfo: any, callbackURL: string): void {
    const url = `${environment.apiUrl}/login`;
    this.http.post(url, {}, { params: loginInfo }).subscribe(
      (user: any) => {
        storage.setItem('App/session', { id: user.id, username: user.username, token: user.token, role: user.role });
        this.isLoggedIn$.next(true);
        this.router.navigate([callbackURL]);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  logout(): void {
    storage.removeItem('App/session');
    this.isLoggedIn$.next(false);
  }

  getUserInfo(): User {
    const sessionInfo = storage.getItem('App/session');
    return sessionInfo;
  }

  getAuthorization(): string {
    const sessionInfo = storage.getItem('App/session');
    return sessionInfo?.token || 'Guest';
  }

}
