import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {catchError, of, tap, Observable, map} from 'rxjs';

interface LoginRequest {
  username: string;
  password: string;
}

interface LoginResponse {
  tokenType: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);

  private _isLoggedIn = signal(false);
  isLoggedIn = this._isLoggedIn.asReadonly();


  login(username: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(
      'http://localhost:8085/api/auth/login',
      { username, password } as LoginRequest,
      { withCredentials: true }
    ).pipe(
      tap(() => this._isLoggedIn.set(true))
    );
  }

  logout(): Observable<any> {
    return this.http.post(
      'http://localhost:8085/api/auth/logout',
      {},
      { withCredentials: true }
    ).pipe(
      tap(() => {
        this._isLoggedIn.set(false);
        this.router.navigate(['/login'], { replaceUrl: true });
      })
    );
  }

  checkAuth(): Observable<boolean> {
    return this.http.get('/api/auth/me', { withCredentials: true }).pipe(
      tap(() => this._isLoggedIn.set(true)),
      map(() => true),
      catchError(() => {
        this._isLoggedIn.set(false);
        return of(false);
      })
    );
  }
}
