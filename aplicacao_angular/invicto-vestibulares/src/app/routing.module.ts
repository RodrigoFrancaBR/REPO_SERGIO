import { PhotoComponent } from './photo/photo.component';

import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, Router, RouterModule } from '@angular/router';

import { FormComponent } from './unidade/form/form.component';
import { TableComponent } from './unidade/table/table.component';


const routes: Routes = [
{path: 'unidade/form', component : FormComponent},
{path: 'unidade/table', component : TableComponent}
];

@NgModule({
  declarations: [PhotoComponent],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
   ],
  exports: [ RouterModule ]
})
export class RoutingModule { }
