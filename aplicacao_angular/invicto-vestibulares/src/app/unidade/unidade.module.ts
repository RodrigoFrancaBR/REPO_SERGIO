import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { UnidadeComponent } from './unidade.component';

@NgModule({
  declarations: [UnidadeComponent],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  exports: [UnidadeComponent]
})
export class UnidadeModule { }
