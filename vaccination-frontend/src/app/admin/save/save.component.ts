import { AdminService } from '../service/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { UserDTO } from '../model/user-dto';

@Component({
  selector: 'app-save',
  templateUrl: './save.component.html',
  styleUrls: ['./save.component.scss']
})
export class SaveComponent implements OnInit {
  private _id: number;
  private _userForm: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private adminService: AdminService, private formBuilder: FormBuilder, private router: Router) {
    this._id = 0;
    this._userForm = this.formBuilder.group({
      dni: ['', Validators.required],
      email: ['', Validators.compose([Validators.email, Validators.required])],
      id: [null],
      name: ['', Validators.required],
      roles: ['', Validators.required],
      surname: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this._id = this.activatedRoute.snapshot.params['id'];
    if (this._id) {
      this.adminService.find(this._id).subscribe({
        next: data => {
          this._userForm.setValue({
            dni: data.dni,
            email: data.email,
            id: data.id,
            name: data.name,
            roles: data.roles,
            surname: data.surname
          });
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
          });
        }
      });
    }
  }

  save(): void {
    let user: UserDTO = this._userForm.value;
    let roles: string[] = [];
    roles.push(this._userForm.controls['roles'].value);
    user.roles = roles;
    this.adminService.register(user).subscribe({
      next: data => {
        Swal.fire({
          color: 'white',
          background: '#A5DC86',
          icon: 'success',
          iconColor: 'white',
          position: 'bottom-end',
          showConfirmButton: false,
          text: data.message,
          timer: 1000 * 4,
          title: 'Éxito',
          toast: true
        }).then(() => {
          this._userForm.setValue({
            dni: '',
            email: '',
            id: null,
            name: '',
            roles: '',
            surname: ''
          });
          this.router.navigateByUrl('admin');
        });
      },
      error: error => {
        console.log(error);
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
        });
      }
    });
  }

  public get id(): number {
    return this._id;
  }
  public get userForm(): FormGroup {
    return this._userForm;
  }
}