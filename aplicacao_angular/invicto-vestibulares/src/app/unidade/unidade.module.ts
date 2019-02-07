import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { UnidadeComponent } from './unidade.component';
import { TableComponent } from './table/table.component';
import { FormComponent } from './form/form.component';
import { PhotoComponent } from './../photo/photo.component';
import { PesquisaComponent } from './pesquisa/pesquisa.component';



@NgModule({
  // componentes pendurados no módulo.
  declarations: [PhotoComponent, UnidadeComponent, FormComponent, TableComponent, PesquisaComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  // componentes que quero usar nos templates de ontros componentes
  exports: [PhotoComponent, UnidadeComponent, FormComponent, TableComponent]
})
export class UnidadeModule { }
