import { AuthService } from 'src/app/login/service/auth.service';
import { CanActivate, Router } from '@angular/router';
import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';
import { TokenStorageService } from 'src/app/login/service/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router, private tokenStorageService: TokenStorageService) { }

  canActivate(): boolean {
    if (this.authService.isLogged() && this.tokenStorageService.user.roles.includes('ROLE_USER')) {
      return true;
    }
    if (this.authService.isLogged() && !this.tokenStorageService.user.roles.includes('ROLE_USER')) {
      Swal.fire({
        color: 'white',
        background: '#F27474',
        icon: 'error',
        iconColor: 'white',
        position: 'bottom-right',
        showConfirmButton: false,
        text: 'No cuenta con los permisos para acceder a este recurso',
        timer: 1000 * 4,
        title: 'Error',
        toast: true
      });
      this.router.navigateByUrl('admin');
      return false;
    }
    if (!this.authService.isLogged()) {
      Swal.fire({
        color: 'white',
        background: '#F27474',
        icon: 'error',
        iconColor: 'white',
        position: 'bottom-right',
        showConfirmButton: false,
        text: 'Para acceder a este recurso, primero debe iniciar sesión',
        timer: 1000 * 4,
        title: 'Error',
        toast: true
      });
    }
    this.router.navigateByUrl('/');
    return false;
  }
}