import { FormComponent } from './form/form.component';
import { PhotoComponent } from './../photo/photo.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { UnidadeComponent } from './unidade.component';
import { TableComponent } from './table/table.component';

@NgModule({
  // componentes pendurados no módulo.
  declarations: [ PhotoComponent, UnidadeComponent, FormComponent, TableComponent ],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  // componentes que quero usar nos templates de ontros componentes
  exports: [ PhotoComponent, UnidadeComponent, FormComponent, TableComponent ]
})
export class UnidadeModule { }
