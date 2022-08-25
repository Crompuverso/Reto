import { AuthService } from '../service/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginDTO } from '../model/login-dto';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { TokenStorageService } from '../service/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private _credentials = { username: '', password: '' }
  private _error = "";
  private _hide: boolean;
  private _userForm: FormGroup;

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router, private tokenStorageService: TokenStorageService) {
    this._hide = true;
    this._userForm = this.formBuilder.group({
      password: ['', Validators.required],
      username: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    if (this.authService.isLogged()) {
      if (this.tokenStorageService.user.roles.includes('ROLE_ADMIN')) {
        this.router.navigateByUrl('admin');
      }
      if (this.tokenStorageService.user.roles.includes('ROLE_USER')) {
        this.router.navigateByUrl('worker');
      }
    }
  }

  login(): void {
    let loginDTO: LoginDTO = this._userForm.value;
    this.authService.login(loginDTO).subscribe({
      next: data => {
        this.tokenStorageService.token = data.token;
        this.tokenStorageService.user = data;
        Swal.fire({
          color: 'white',
          background: '#A5DC86',
          icon: 'success',
          iconColor: 'white',
          position: 'bottom-right',
          showConfirmButton: false,
          text: this.tokenStorageService.user.name + ' ' + this.tokenStorageService.user.surname,
          timer: 1000 * 4,
          title: 'Bienvenido',
          toast: true
        });
        if (this.tokenStorageService.user.roles.includes('ROLE_ADMIN')) {
          this.router.navigateByUrl('admin');
        }
        else if (this.tokenStorageService.user.roles.includes('ROLE_USER')) {
          this.router.navigateByUrl('worker');
        }
        else {
          this.authService.logout();
        }
      },
      error: error => {
        Swal.fire({
          color: 'white',
          background: '#F27474',
          icon: 'error',
          iconColor: 'white',
          position: 'bottom-right',
          showConfirmButton: false,
          text: error.error.message,
          timer: 1000 * 4,
          title: error.error.error,
          toast: true
        }).then(() => {
          this._userForm.setValue({
            password: '',
            username: ''
          });
        });
      }
    });
  }

  public get credentials(): any {
    return this._credentials;
  }
  public get error() {
    return this._error
  }
  public get hide(): boolean {
    return this._hide;
  }
  public get userForm(): FormGroup {
    return this._userForm;
  }

  public set hide(_hide: boolean) {
    this._hide = _hide;
  }
}