import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from 'src/app/login/service/token-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private tokenHeaderKey: string;

  constructor(private tokenStorageService: TokenStorageService) {
    this.tokenHeaderKey = 'Authorization';
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authRequest: HttpRequest<any> = request;
    const token: String = this.tokenStorageService.token;
    if (token) {
      authRequest = request.clone({ headers: request.headers.set(this.tokenHeaderKey, 'Bearer ' + token) });
    }
    return next.handle(authRequest);
  }
}

export const authInterceptorProviders = [{
  provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true
}];