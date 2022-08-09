import { NgModule } from '@angular/core';
import { RegisterComponent } from '../register/register.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{ path: 'register', component: RegisterComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }