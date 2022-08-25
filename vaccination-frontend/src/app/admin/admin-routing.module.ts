import { AdminGuard } from 'src/assets/guard/admin.guard';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SaveComponent } from './save/save.component';

const routes: Routes = [{ path: '', component: HomeComponent, canActivate: [AdminGuard] },
{ path: 'register', component: SaveComponent, canActivate: [AdminGuard] },
{ path: 'update/:id', component: SaveComponent, canActivate: [AdminGuard] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }