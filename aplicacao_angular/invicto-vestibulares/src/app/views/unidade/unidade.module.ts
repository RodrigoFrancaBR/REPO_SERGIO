import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UnidadeRaizComponent } from './unidade-raiz/unidade-raiz.component';
import { UnidadeGridComponent } from './unidade-raiz/unidade-grid/unidade-grid.component';
import { UnidadeFormComponent } from './unidade-raiz/unidade-form/unidade-form.component';


@NgModule({
  declarations: [ UnidadeRaizComponent, UnidadeGridComponent, UnidadeFormComponent ],
  imports: [
    CommonModule
  ]
})
export class UnidadeModule { }
