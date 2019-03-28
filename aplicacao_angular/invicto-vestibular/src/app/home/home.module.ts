import { SigninComponent } from './signin/signin.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { VmessageModule } from 'components/vmessage/vmessage.module';

@NgModule({
  declarations: [SigninComponent],
  imports: [
    CommonModule, ReactiveFormsModule, VmessageModule
  ]
})
export class HomeModule { }
