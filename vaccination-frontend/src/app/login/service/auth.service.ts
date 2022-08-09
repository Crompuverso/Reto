import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginDTO } from '../model/login-dto';
import { UserDTO } from '../model/user-dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private httpHeaders: {};
  private url: String;

  constructor(private httpClient: HttpClient) {
    this.httpHeaders = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };
    this.url = 'http://localhost:8080/api/auth/';
  }

  login(login: LoginDTO): Observable<any> {
    return this.httpClient.post(this.url + 'login', login, this.httpHeaders);
  }

  register(user: UserDTO): Observable<any> {
    return this.httpClient.post(this.url + 'register', user, this.httpHeaders);
  }
}