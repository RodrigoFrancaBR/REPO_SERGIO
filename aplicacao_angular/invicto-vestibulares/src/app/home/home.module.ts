import { PhotoComponent } from './../photo/photo.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SinginComponent } from './singin/singin.component';
import { ReactiveFormsModule } from '@angular/forms';
import { VmessageModule } from '../components/vmessage/vmessage.module';


@NgModule({
  declarations: [SinginComponent, PhotoComponent],
  imports: [
    CommonModule, ReactiveFormsModule, VmessageModule
  ],
  exports: [PhotoComponent]
})
export class HomeModule { }
