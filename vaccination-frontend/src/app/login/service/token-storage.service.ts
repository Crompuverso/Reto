import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private tokenKey: string;
  private userKey: string;

  constructor() {
    this.tokenKey = 'auth-token';
    this.userKey = 'auth-user';
  }
  public get token(): string {
    return sessionStorage.getItem(this.tokenKey)!;
  }
  public get user(): any {
    return JSON.parse(sessionStorage.getItem(this.userKey)!);
  }

  public set token(token: string) {
    window.sessionStorage.removeItem(this.tokenKey);
    window.sessionStorage.setItem(this.tokenKey, token);
  }
  public set user(user: any) {
    window.sessionStorage.removeItem(this.userKey);
    window.sessionStorage.setItem(this.userKey, JSON.stringify(user));
  }
}