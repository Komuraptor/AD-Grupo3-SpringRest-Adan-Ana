import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { AuthService } from '@lib/services';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(
      private _authService: AuthService,
  ) {

  }

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler,
  ): Observable<HttpEvent<unknown>> {
    const isLoggedIn = this._authService.isLoggedIn;
    const token = isLoggedIn ? this._authService.getAuthorization() : 'Guest';
    const isApiUrl = request.url.startsWith(environment.apiUrl);

    if (isLoggedIn && isApiUrl) {
      request = request.clone({
        setHeaders: {
          Authorization: `${token}`,
        },
      });
    }

    return next.handle(request);
  }
}
