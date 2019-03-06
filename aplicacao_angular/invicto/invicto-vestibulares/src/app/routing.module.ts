import { AdminComponent } from './views/admin/admin.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';


import { ErrorsModule } from './errors/errors.module';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { SinginComponent } from './home/singin/singin.component';
import { HomeModule } from './home/home.module';
import { OperadorComponent } from './views/operador/operador.component';
// const variavel : any = {};
const routes: Routes = [
  { path: '', component: SinginComponent},
  // { path: 'user/:tipo', component: variavel},
  { path: 'user/admin', component: AdminComponent},
  { path: 'user/operador', component: OperadorComponent},
  { path: '**', component: NotFoundComponent }
];
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HomeModule,
    ErrorsModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class RoutingModule {}
