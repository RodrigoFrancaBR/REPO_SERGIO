import { PhotoComponent } from './../photo/photo.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { UnidadeComponent } from './unidade.component';

@NgModule({
  // componentes pendurados no módulo.
  declarations: [ UnidadeComponent, PhotoComponent ],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  // componentes que quero usar nos templates de ontros componentes
  exports: [ UnidadeComponent, PhotoComponent ]
})
export class UnidadeModule { }
