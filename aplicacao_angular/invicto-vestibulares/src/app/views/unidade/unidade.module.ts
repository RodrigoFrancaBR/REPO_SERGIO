import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormComponent } from 'src/app/unidade/form/form.component';

import { UnidadeRaizComponent } from './unidade-raiz/unidade-raiz.component';
import { UnidadeGridComponent } from './unidade-raiz/unidade-grid/unidade-grid.component';
import { UnidadeFormComponent } from './unidade-raiz/unidade-form/unidade-form.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [UnidadeRaizComponent, UnidadeGridComponent, UnidadeFormComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ]
})
export class UnidadeModule { }
