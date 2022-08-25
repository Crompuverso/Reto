import { HttpClient } from '@angular/common/http';
import { httpHeaders, url } from 'src/assets/utils/configuration';
import { Injectable } from '@angular/core';
import { LoginDTO } from '../model/login-dto';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private tokenKey: string;
  private userKey: string;

  constructor(private httpClient: HttpClient, private router: Router) {
    this.tokenKey = 'auth-token';
    this.userKey = 'auth-user';
  }

  public isLogged(): boolean {
    let token = sessionStorage.getItem(this.tokenKey);
    if (token == null || token == undefined || token == '') {
      return false;
    }
    return true;
  }

  public login(login: LoginDTO): Observable<any> {
    return this.httpClient.post(url + 'auth/login', login, httpHeaders);
  }

  public logout(): void {
    window.sessionStorage.removeItem(this.tokenKey);
    window.sessionStorage.removeItem(this.userKey);
    this.router.navigateByUrl('/');
  }
}