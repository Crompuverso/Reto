import { AdminRoutingModule } from './admin-routing.module';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { NgModule } from '@angular/core';
import { SaveComponent } from './save/save.component';
import { TemplateModule } from '../template/template.module';

@NgModule({
  declarations: [
    HomeComponent,
    SaveComponent
  ],
  imports: [
    AdminRoutingModule,
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    MatTableModule,
    ReactiveFormsModule,
    TemplateModule
  ]
})
export class AdminModule { }