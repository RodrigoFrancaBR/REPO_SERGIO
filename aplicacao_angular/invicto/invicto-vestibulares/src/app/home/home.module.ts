import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SinginComponent } from './singin/singin.component';
import { VmessageModule } from './../components/vmessage/vmessage.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [SinginComponent],
  imports: [
    CommonModule, ReactiveFormsModule, VmessageModule, HttpClientModule
  ]
})
export class HomeModule { }
