import { AuthService } from 'src/app/login/service/auth.service';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/login/service/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router, private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.router.url;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigateByUrl('/');
  }

  redirect(url: string): void {
    this.router.navigateByUrl(url);
  }

  public get isAdmin(): boolean {
    return this.tokenStorageService.user.roles.includes("ROLE_ADMIN");
  }
  public get url(): string {
    return this.router.url;
  }
}