import { VmessageModule } from './../components/vmessage/vmessage.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SinginComponent } from './singin/singin.component';
import { ReactiveFormsModule } from '@angular/forms';
import { VmessageComponent } from '../components/vmessage/vmessage.component';

@NgModule({
  declarations: [SinginComponent],
  imports: [
    CommonModule, ReactiveFormsModule, VmessageModule
  ]
})
export class HomeModule { }
