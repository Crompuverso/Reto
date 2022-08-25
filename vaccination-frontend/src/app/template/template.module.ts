import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { MatButtonModule } from '@angular/material/button';

import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NavbarComponent } from './navbar/navbar.component';
import { NgModule } from '@angular/core';
@NgModule({
  declarations: [
    NavbarComponent,
    FooterComponent
  ],
  exports: [NavbarComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule
  ]
})
export class TemplateModule { }
