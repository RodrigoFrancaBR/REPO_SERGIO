import { HomeModule } from './home/home.module';
import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, Router, RouterModule } from '@angular/router';

import { UnidadeModule } from './unidade/unidade.module';
import { PrincipalComponent } from './views/principal/principal.component';
import { FormComponent } from './unidade/form/form.component';
import { TableComponent } from './unidade/table/table.component';
import { UnidadeComponent } from './unidade/unidade.component';
import { SinginComponent } from './home/singin/singin.component';

export const routes: Routes = [
  { path: 'unidade/form', component: FormComponent },
  { path: 'unidade/table', component: TableComponent },
  { path: 'unidade', component: UnidadeComponent },
  { path: '', component: SinginComponent }
  // { path: '', component: PrincipalComponent }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HomeModule,
    UnidadeModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class RoutingModule { }
