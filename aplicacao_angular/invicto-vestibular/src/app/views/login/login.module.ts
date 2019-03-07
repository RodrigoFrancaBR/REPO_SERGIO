import { VmessageModule } from './../../../components/vmessage/vmessage.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginComponent } from './login.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule, ReactiveFormsModule, VmessageModule
  ]
})
export class LoginModule { }
