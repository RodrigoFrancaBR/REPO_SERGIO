import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SinginComponent } from './singin/singin.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [SinginComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ]
})
export class HomeModule { }
