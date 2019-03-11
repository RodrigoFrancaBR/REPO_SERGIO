import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from './login.component';
import { VmessageModule } from './../../../components/vmessage/vmessage.module';

@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule, ReactiveFormsModule, VmessageModule, HttpClientModule
  ]
})
export class LoginModule { }
