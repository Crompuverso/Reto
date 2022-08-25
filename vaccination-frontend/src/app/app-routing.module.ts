import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{ path: '', loadChildren: () => import('./login/login.module').then(m => m.LoginModule) },
{ path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
{ path: 'worker', loadChildren: () => import('./worker/worker.module').then(m => m.WorkerModule) },
{ path: '**', redirectTo: '' }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }