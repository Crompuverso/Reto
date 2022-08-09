import { AuthService } from './service/auth.service';
import { Component, OnInit } from '@angular/core';
import { LoginDTO } from './model/login-dto';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TokenStorageService } from './service/token-storage.service';
import swal from 'sweetalert2';

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
  }

  login(): void {
    let loginDTO: LoginDTO = this._userForm.value;
    this.authService.login(loginDTO).subscribe({
      next: data => {
        console.log(data)
        this.tokenStorageService.token = data.token;
        this.tokenStorageService.user = data;
        if (this.tokenStorageService.user.roles.includes('ROLE_ADMIN')) {
          swal.fire({
            color: 'white',
            background: '#A5DC86',
            icon: 'success',
            iconColor: 'white',
            position: 'bottom-end',
            text: this.tokenStorageService.user.name + ' ' + this.tokenStorageService.user.surname,
            timer: 1000 * 1,
            title: 'Bienvenido',
            toast: true
          });
          this.router.navigateByUrl('/register');
        }
        if (this.tokenStorageService.user.roles.includes('ROLE_USER')) {
          this.router.navigateByUrl('/profile');
        }
      },
      error: error => {
        swal.fire({
          color: 'white',
          background: '#F27474',
          icon: 'error',
          iconColor: 'white',
          position: 'bottom-right',
          showConfirmButton: false,
          text: error.message,
          timer: 1000 * 1,
          title: 'Error',
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