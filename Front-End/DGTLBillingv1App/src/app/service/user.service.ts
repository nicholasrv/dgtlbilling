import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { CustomHttpResponse, Profile } from '../interface/appstates';
import { Observable, catchError, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly server: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  login$ = (email: string, password: string) =>
    <Observable<CustomHttpResponse<Profile>>>
    this.http
      .post<CustomHttpResponse<Profile>>(`${this.server}/user/login`, {
        email,
        password
      })
      .pipe(
        tap(console.log),
        catchError(this.handleError)
        );

  verifyCode$ = (email: string, code: string) =>
    <Observable<CustomHttpResponse<Profile>>>
    this.http
      .get<CustomHttpResponse<Profile>>(`${this.server}/user/verify/code/${email}/${code}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
        );

  profile$ = () =>
    <Observable<CustomHttpResponse<Profile>>>
      this.http
        .get<CustomHttpResponse<Profile>>(`${this.server}/user/profile`, { headers: new HttpHeaders().set('Authorization', 'Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJOSUNIT0xBU1JWX0xUREEiLCJhdWQiOiJESUdJVEFMX0JJTExJTkdfU0VSVklDRSIsImlhdCI6MTY5Mzc3MDIxMCwic3ViIjoibmljaG9sYXNydkBnbWFpbC5jb20iLCJhdXRob3JpdGllcyI6WyJSRUFEOlVTRVIiLCJSRUFEOkNVU1RPTUVSIl0sImV4cCI6MTY5Mzc3MjAxMH0.6jrLuK9GsFb8jxy-o-vnhnEJaBXObH1Rl-s5oNj1BWjgqH_wIxASOBpTNRoiL8GWe7GWLaE36rxTdlj0JglNoA')})
        .pipe(
          tap(console.log),
          catchError(this.handleError)
          );

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage: string;
    if (error.error instanceof ErrorEvent) {
      errorMessage = `A client error occurred - ${error.error.message}`;
    } else {
      if (error.error.reason) {
        errorMessage = error.error.reason;
        console.log(errorMessage);
      } else {
        errorMessage = `An error occurred - Error code ${error.status}`;
      }
    }
    return throwError(() => errorMessage);
  }
}
