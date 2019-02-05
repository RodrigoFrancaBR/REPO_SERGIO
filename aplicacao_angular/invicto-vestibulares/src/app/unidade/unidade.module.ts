import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { UnidadeComponent } from './unidade.component';
import { FormComponent } from './form/form.component';
import { TableComponent } from './table/table.component';

@NgModule({
  declarations: [UnidadeComponent, FormComponent, TableComponent],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  exports: [UnidadeComponent]
})
export class UnidadeModule { }
