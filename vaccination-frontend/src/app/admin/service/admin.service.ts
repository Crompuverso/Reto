import { HttpClient } from '@angular/common/http';
import { httpHeaders, url } from 'src/assets/utils/configuration';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDTO } from '../model/user-dto';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient: HttpClient) { }

  delete(id: number): Observable<any> {
    return this.httpClient.delete(url + 'user/delete/' + id);
  }

  find(id: number): Observable<UserDTO> {
    return this.httpClient.get<UserDTO>(url + 'user/' + id);
  }

  findAll(): Observable<UserDTO[]> {
    return this.httpClient.get<UserDTO[]>(url + 'user/users');
  }

  register(user: UserDTO): Observable<any> {
    return this.httpClient.post(url + 'user/save', user, httpHeaders);
  }
}