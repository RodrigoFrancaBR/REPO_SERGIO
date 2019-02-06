
import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, Router, RouterModule } from '@angular/router';

import { UnidadeModule } from './unidade/unidade.module';
import { FormComponent } from './unidade/form/form.component';
import { TableComponent } from './unidade/table/table.component';

export const routes: Routes = [
{path: 'unidade/form', component : FormComponent},
{path: 'unidade/table', component : TableComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    UnidadeModule,
    RouterModule.forRoot(routes)
   ],
  exports: [ RouterModule ]
})
export class RoutingModule { }
