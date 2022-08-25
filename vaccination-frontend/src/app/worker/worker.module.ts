import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { TemplateModule } from '../template/template.module';
import { WorkerRoutingModule } from './worker-routing.module';

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    TemplateModule,
    WorkerRoutingModule
  ]
})
export class WorkerModule { }