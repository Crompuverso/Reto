import { AuthService } from '../login/service/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDTO } from '../login/model/user-dto';
import swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  private _userForm: FormGroup;

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) {
    this._userForm = this.formBuilder.group({
      dni: ['', Validators.required],
      email: ['', Validators.compose([Validators.email, Validators.required])],
      name: ['', Validators.required],
      roles: ['', Validators.required],
      surname: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  logout(): void {
    console.log("Has salido con éxito");
    this.router.navigateByUrl('/');
  }

  save(): void {
    let user: UserDTO = this._userForm.value;
    let roles: string[] = [];
    roles.push(this._userForm.controls['roles'].value);
    user.roles = roles;
    this.authService.register(user).subscribe({
      next: data => {
        swal.fire({
          color: 'white',
          background: '#A5DC86',
          icon: 'success',
          iconColor: 'white',
          position: 'bottom-end',
          text: data.message,
          timer: 1000 * 1,
          title: 'Excelente',
          toast: true
        }).then(() => {
          this._userForm.setValue({
            dni: '',
            email: '',
            name: '',
            roles: '',
            surname: ''
          });
        });
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
            dni: '',
            email: '',
            name: '',
            roles: '',
            surname: ''
          });
        });
      }
    });
  }

  public get userForm(): FormGroup {
    return this._userForm;
  }
}