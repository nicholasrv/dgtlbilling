import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Key } from '../enum/key.enum';

@Injectable({ providedIn: 'root' })
export class TokenInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(request.url.includes('verify') || request.url.includes('login') || request.url.includes('register') || request.url.includes('refresh') || request.url.includes('resetpassword')) {
      return next.handle(request);
    }
    return next.handle(this.addAuthorizationTokenHeader(request, localStorage.getItem(Key.TOKEN)));
  }
  addAuthorizationTokenHeader(request: HttpRequest<unknown>, token: string): HttpRequest<any> {
    return request.clone({setHeaders: {Authorization: `Bearer ${token}`}});
  }
}
