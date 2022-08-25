import { AdminService } from '../service/admin.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { TokenStorageService } from 'src/app/login/service/token-storage.service';
import { UserDTO } from '../model/user-dto';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private _dataSource: MatTableDataSource<UserDTO>;
  private _displayedColumns: string[];

  constructor(private adminService: AdminService, private router: Router, private tokenStorageService: TokenStorageService) {
    this._displayedColumns = ['dni', 'name', 'surname', 'email', 'roles', 'actions'];
    this._dataSource = new MatTableDataSource<UserDTO>();
  }

  ngOnInit(): void {
    this.load();
  }

  delete(id: number, name: string, surname: string): void {
    Swal.fire({
      cancelButtonColor: '#D33',
      confirmButtonColor: '#3085D6',
      confirmButtonText: 'Si, eliminar',
      icon: 'warning',
      showCancelButton: true,
      text: 'Seguro que desea eliminar al usuario: ' + name + ' ' + surname,
      title: 'Está seguro'
    }).then(result => {
      if (result.isConfirmed) {
        this.adminService.delete(id).subscribe({
          next: data => {
            console.log(data);
            Swal.fire({
              color: 'white',
              background: '#A5DC86',
              icon: 'success',
              iconColor: 'white',
              position: 'bottom-right',
              showConfirmButton: false,
              text: name + ' ' + surname + ' eliminado con éxito',
              timer: 1000 * 4,
              title: data.message,
              toast: true
            });
            this.load();
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
    });
  }

  filter(event: Event): void {
    const filterValue: string = (event.target as HTMLInputElement).value;
    this._dataSource.filter = filterValue.trim().toLowerCase();
    if (this._dataSource.paginator) {
      this.dataSource.paginator?.firstPage();
    }
  }

  load(): void {
    this.adminService.findAll().subscribe({
      next: data => {
        this._dataSource.data = data;
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

  update(id: number): void {
    this.router.navigateByUrl('admin/update/' + id);
  }

  public get dataSource(): MatTableDataSource<UserDTO> {
    return this._dataSource;
  }
  public get displayedColumns(): string[] {
    return this._displayedColumns;
  }
  public get username(): string {
    return this.tokenStorageService.user.username;
  }
}