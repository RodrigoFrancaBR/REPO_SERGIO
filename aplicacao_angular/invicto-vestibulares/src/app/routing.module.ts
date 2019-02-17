import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, Router, RouterModule } from '@angular/router';

import { UnidadeModule } from './unidade/unidade.module';
import { PrincipalComponent } from './views/principal/principal.component';
import { FormComponent } from './unidade/form/form.component';
import { TableComponent } from './unidade/table/table.component';
import { UnidadeComponent } from './unidade/unidade.component';
import { SinginComponent } from './home/singin/singin.component';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { HomeModule } from './home/home.module';
import { ErrorsModule } from './errors/errors.module';


export const routes: Routes = [
  { path: 'unidade/form', component: FormComponent },
  { path: 'unidade/table', component: TableComponent },
  { path: 'unidade', component: UnidadeComponent },
  { path: 'user/:userName', component: PrincipalComponent },
  { path: '', component: SinginComponent },
  { path: '**', component: NotFoundComponent }
  //
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HomeModule,
    UnidadeModule,
    ErrorsModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class RoutingModule {}
